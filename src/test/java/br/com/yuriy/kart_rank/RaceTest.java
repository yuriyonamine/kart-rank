package br.com.yuriy.kart_rank;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

import br.com.yuriy.kart_rank.exception.PilotNotRegisteredInRaceException;
import br.com.yuriy.kart_rank.exception.UnfinishedRaceException;
import br.com.yuriy.kart_rank.race.PilotImp;
import br.com.yuriy.kart_rank.race.Race;
import br.com.yuriy.kart_rank.reader.LogReaderImp;

public class RaceTest {

	@Test
	public void findFirstPilotArrivalPosition() throws FileNotFoundException {
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		PilotImp pilot = new PilotImp(38, "");

		int positionMassa = race.findArrivalPosition(pilot);
		assertEquals(1, positionMassa);
	}

	@Test
	public void notFinishedArrivalPosition() throws FileNotFoundException {
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		PilotImp pilot = new PilotImp(11, "");
		int positionVettel = race.findArrivalPosition(pilot);
		assertEquals(6, positionVettel);
	}

	@Test(expected = PilotNotRegisteredInRaceException.class)
	public void pilotNotRegisteredInRace() throws FileNotFoundException {
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		PilotImp pilot = new PilotImp(0, "");
		race.findArrivalPosition(pilot);
	}

	@Test
	public void piloteCompletedJustOneTurn() throws FileNotFoundException {
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		PilotImp pilot = new PilotImp(11, "");

		int turnsQuantity = race.findCompletedTurnsQuantity(pilot);

		assertEquals(3, turnsQuantity);
	}

	@Test
	public void piloteCompletedFourTurns() throws FileNotFoundException {
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		PilotImp pilot = new PilotImp(38, "");

		int turnsQuantity = race.findCompletedTurnsQuantity(pilot);

		assertEquals(4, turnsQuantity);
	}

	@Test
	public void twoMinutesRaceDuration() throws FileNotFoundException {
		Duration expectedDuration = Duration.parse("PT2M");
		LogReaderImp logReader = new LogReaderImp(createOneMinuteRaceDurationLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		Duration raceDuration = race.calculateRaceDuration();

		assertEquals(expectedDuration, raceDuration);
	}

	@Test(expected = UnfinishedRaceException.class)
	public void calculateUnfinishedRaceDuration() {
		PilotMock pilot = new PilotMock(1, "Ricardo(Slow)");
		TurnMock turn = new TurnMock();
		turn.setTurnHour(LocalTime.parse("23:49:08.277"));
		turn.setPilot(pilot);
		turn.setNumber(1);
		turn.setDuration(Duration.parse("PT1M02.852S"));
		turn.setTurnSpeedAverage(new BigDecimal(10.0f));
		
		Race race = new Race(Arrays.asList(turn), Arrays.asList(pilot));
		race.calculateRaceDuration();
	}
	
	@Test
	public void fourMinutesElevenSecondsFiveHundredSeventyEightMillisecondsRaceDuration() throws FileNotFoundException {
		Duration expectedDuration = Duration.parse("PT4M11.578S");
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		Duration raceDuration = race.calculateRaceDuration();

		assertEquals(expectedDuration, raceDuration);
	}

	@Test
	public void calculateTimeBetweenSecondPilotAndFirst() throws FileNotFoundException {
		Duration expectedTime = Duration.parse("PT5.583S");
		LogReaderImp logReader = new LogReaderImp(createLogFile());

		PilotImp pilot = new PilotImp(33, "");

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		Duration distance = race.calculateDistanceFromPilotAndWinner(pilot);
		assertEquals(expectedTime, distance);
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

	private File createOneMinuteRaceDurationLogFile() throws FileNotFoundException {
		File logFile = new File("logFileTest.txt");
		PrintWriter print = new PrintWriter(logFile);
		print.println("Hora                   Piloto         Nº Volta   Tempo Volta      Velocidade média da volta");
		print.println("23:49:00.000      038 – F.MASSA             1     1:00.000            44,275");
		print.println("23:50:00.000      038 – F.MASSA             4     1:00.000            44,321");

		print.close();

		return logFile;
	}

}
