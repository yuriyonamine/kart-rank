package br.com.yuriy.kart_rank;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import org.junit.Test;

import br.com.yuriy.kart_rank.race.PilotImp;
import br.com.yuriy.kart_rank.race.Race;
import br.com.yuriy.kart_rank.race.Turn;
import br.com.yuriy.kart_rank.reader.LogReaderImp;
import br.com.yuriy.kart_rank.statistics.RaceStatistics;

public class RaceStatisticsTest {

	@Test
	public void findBestPilotTurn() throws FileNotFoundException {
		Duration expectedTurnTime = Duration.parse("PT1M02.769S");
		LogReaderImp logReader = new LogReaderImp(createLogFile());
		
		PilotImp pilot = new PilotImp(38, "");
		
		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		RaceStatistics raceStatistic = new RaceStatistics(race);
		Turn bestPilotTurn = raceStatistic.findBestPilotTurn(pilot);

		assertEquals(expectedTurnTime, bestPilotTurn.getDuration());
	}

	@Test
	public void findBestRaceTurn() throws FileNotFoundException {
		Duration expectedTurnTime = Duration.parse("PT1M02.769S");
		LogReaderImp logReader = new LogReaderImp(createLogFile());
		
		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		RaceStatistics raceStatistic = new RaceStatistics(race);
		Turn bestPilotTurn = raceStatistic.findBestRaceTurn();

		assertEquals(expectedTurnTime, bestPilotTurn.getDuration());
	}

	@Test
	public void calculateSpeedRaceAverageOfFasterPilot() throws FileNotFoundException {
		BigDecimal expectedSpeed = new BigDecimal(44.245).setScale(3, RoundingMode.HALF_DOWN);
		LogReaderImp logReader = new LogReaderImp(createLogFile());
		
		PilotImp pilot = new PilotImp(38, "");
		
		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		RaceStatistics raceStatistic = new RaceStatistics(race);
		BigDecimal speed = raceStatistic.calculateSpeedRaceAverage(pilot);
		assertEquals(expectedSpeed, speed);
	}

	@Test
	public void calculateSpeedRaceAverageOfSecondFasterPilot() throws FileNotFoundException {
		BigDecimal expectedSpeed = new BigDecimal(43.468).setScale(3, RoundingMode.HALF_DOWN);
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		PilotImp pilot = new PilotImp(33, "");
		
		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		RaceStatistics raceStatistic = new RaceStatistics(race);
		BigDecimal speed = raceStatistic.calculateSpeedRaceAverage(pilot);
		assertEquals(expectedSpeed, speed);
	}
	
	private File createLogFile() throws FileNotFoundException {
		File logFile = new File("logFileTest.txt");
		PrintWriter print = new PrintWriter(logFile);
		print.println("Hora                   Piloto         Nº Volta   Tempo Volta      Velocidade média da volta");
		print.println("23:49:08.277      038 – F.MASSA             1     1:02.852            44,275");
		print.println("23:49:10.858      033 – R.BARRICHELLO       1     1:04.352            43,243");
		print.println("23:49:11.075      002 – K.RAIKKONEN         1     1:04.108            43,408");
		print.println("23:49:12.667      023 – M.WEBBER            1     1:04.414            43,202");
		print.println("23:49:30.976      015 – F.ALONSO            1     1:18.456            35,47");
		print.println("23:50:11.447      038 – F.MASSA             2     1:03.170            44,053");
		print.println("23:50:14.860      033 – R.BARRICHELLO       2     1:04.002            43,48");
		print.println("23:50:15.057      002 – K.RAIKKONEN         2     1:03.982            43,493");
		print.println("23:50:17.472      023 – M.WEBBER            2     1:04.805            42,941");
		print.println("23:50:37.987      015 – F.ALONSO            2     1:07.011            41,528");
		print.println("23:51:14.216      038 – F.MASSA             3     1:02.769            44,334");
		print.println("23:51:18.576      033 – R.BARRICHELLO       3     1:03.716            43,675");
		print.println("23:51:19.044      002 – K.RAIKKONEN         3     1:03.987            43,49");
		print.println("23:51:21.759      023 – M.WEBBER            3     1:04.287            43,287");
		print.println("23:51:46.691      015 – F.ALONSO            3     1:08.704            40,504");
		print.println("23:52:01.796      011 – S.VETTEL            1     3:31.315            13,169");
		print.println("23:52:17.003      038 – F.MASS              4     1:02.787            44,321");
		print.println("23:52:22.586      033 – R.BARRICHELLO       4     1:04.010            43,474");
		print.println("23:52:22.120      002 – K.RAIKKONEN         4     1:03.076            44,118");
		print.println("23:52:25.975      023 – M.WEBBER            4     1:04.216            43,335");
		print.println("23:53:06.741      015 – F.ALONSO            4     1:20.050            34,763");
		print.println("23:53:39.660      011 – S.VETTEL            2     1:37.864            28,435");
		print.println("23:54:57.757      011 – S.VETTEL            3     1:18.097            35,633");
		print.close();

		return logFile;
	}

}
