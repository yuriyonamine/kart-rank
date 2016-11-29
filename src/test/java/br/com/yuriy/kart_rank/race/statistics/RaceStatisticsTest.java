package br.com.yuriy.kart_rank.race.statistics;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import org.junit.Test;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.PilotImp;
import br.com.yuriy.kart_rank.race.PilotMock;
import br.com.yuriy.kart_rank.race.RaceMock;
import br.com.yuriy.kart_rank.race.Turn;
import br.com.yuriy.kart_rank.statistics.RaceStatistics;

public class RaceStatisticsTest {

	@Test
	public void findBestPilotTurn() throws FileNotFoundException {
		Duration expectedTurnTime = Duration.parse("PT1M02.769S");

		Pilot pilot = new PilotMock(38, "");

		RaceMock race = new RaceMock();

		RaceStatistics raceStatistic = new RaceStatistics(race);
		Turn bestPilotTurn = raceStatistic.findBestPilotTurn(pilot);
		race.verifyGetTurnsCall();

		assertEquals(expectedTurnTime, bestPilotTurn.getDuration());
	}

	@Test
	public void findBestRaceTurn() throws FileNotFoundException {
		Duration expectedTurnTime = Duration.parse("PT1M02.769S");

		RaceMock race = new RaceMock();

		RaceStatistics raceStatistic = new RaceStatistics(race);
		Turn bestPilotTurn = raceStatistic.findBestRaceTurn();
		race.verifyGetTurnsCall();
		
		assertEquals(expectedTurnTime, bestPilotTurn.getDuration());
	}

	@Test
	public void calculateSpeedRaceAverageOfFasterPilot() throws FileNotFoundException {
		BigDecimal expectedSpeed = new BigDecimal(44.245).setScale(3, RoundingMode.HALF_DOWN);

		Pilot pilot = new PilotImp(38, "");

		RaceMock race = new RaceMock();
		RaceStatistics raceStatistic = new RaceStatistics(race);
		BigDecimal speed = raceStatistic.calculateSpeedRaceAverage(pilot);
		race.verifyGetTurnsCall();
		
		assertEquals(expectedSpeed, speed);
	}

	@Test
	public void calculateSpeedRaceAverageOfSecondFasterPilot() throws FileNotFoundException {
		BigDecimal expectedSpeed = new BigDecimal(43.473).setScale(3, RoundingMode.HALF_DOWN);

		PilotImp pilot = new PilotImp(33, "");	

		RaceMock race = new RaceMock();
		RaceStatistics raceStatistic = new RaceStatistics(race);
		BigDecimal speed = raceStatistic.calculateSpeedRaceAverage(pilot);
		race.verifyGetTurnsCall();
		
		assertEquals(expectedSpeed, speed);
	}

}
