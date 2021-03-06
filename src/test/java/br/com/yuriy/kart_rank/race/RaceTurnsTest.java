package br.com.yuriy.kart_rank.race;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.yuriy.kart_rank.exception.NoTurnException;
import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.PilotImp;
import br.com.yuriy.kart_rank.race.RaceImp;
import br.com.yuriy.kart_rank.race.Turn;

public class RaceTurnsTest {
	private List<Pilot> pilots;
	private List<Turn> turns;

	@Before
	public void initialize() {
		prepareData();
	}

	@Test
	public void pilotCompletedJustOneTurn() {
		RaceImp raceImp = new RaceImp(turns, pilots);
		PilotImp pilot = new PilotImp(11, "");

		int turnsQuantity = raceImp.findCompletedTurnsQuantity(pilot);

		assertEquals(3, turnsQuantity);
	}

	@Test
	public void pilotCompletedFourTurns() {
		RaceImp raceImp = new RaceImp(turns, pilots);

		PilotImp pilot = new PilotImp(38, "");

		int turnsQuantity = raceImp.findCompletedTurnsQuantity(pilot);

		assertEquals(4, turnsQuantity);
	}
	
	@Test(expected=NoTurnException.class)
	public void pilotHasNoTurns() {
		PilotImp pilot = new PilotImp(1, "");
		RaceImp raceImp = new RaceImp(Arrays.asList(), Arrays.asList(pilot));

		raceImp.findCompletedTurnsQuantity(pilot);
	}

	private void prepareData() {
		PilotMock pilot1 = new PilotMock(38, "F.MASSA");
		TurnMock turn1Pilot1 = new TurnMock();
		turn1Pilot1.setTurnHour(LocalTime.parse("23:49:08.277"));
		turn1Pilot1.setPilot(pilot1);
		turn1Pilot1.setNumber(1);
		turn1Pilot1.setDuration(Duration.parse("PT1M02.852S"));
		turn1Pilot1.setTurnSpeedAverage(new BigDecimal(44.275f));

		TurnMock turn2Pilot1 = new TurnMock();
		turn2Pilot1.setTurnHour(LocalTime.parse("23:50:11.447"));
		turn2Pilot1.setPilot(pilot1);
		turn2Pilot1.setNumber(2);
		turn2Pilot1.setDuration(Duration.parse("PT1M3.170S"));
		turn2Pilot1.setTurnSpeedAverage(new BigDecimal(44.053f));

		TurnMock turn3Pilot1 = new TurnMock();
		turn3Pilot1.setTurnHour(LocalTime.parse("23:51:14.216"));
		turn3Pilot1.setPilot(pilot1);
		turn3Pilot1.setNumber(3);
		turn3Pilot1.setDuration(Duration.parse("PT1M02.769S"));
		turn3Pilot1.setTurnSpeedAverage(new BigDecimal(44.334f));

		TurnMock turn4Pilot1 = new TurnMock();
		turn4Pilot1.setTurnHour(LocalTime.parse("23:52:17.003"));
		turn4Pilot1.setPilot(pilot1);
		turn4Pilot1.setNumber(4);
		turn4Pilot1.setDuration(Duration.parse("PT1M02.787S"));
		turn4Pilot1.setTurnSpeedAverage(new BigDecimal(44.321f));

		Pilot pilot2 = new PilotMock(33, "R.BARRICHELLO");
		TurnMock turn4Pilot2 = new TurnMock();
		turn4Pilot2.setTurnHour(LocalTime.parse("23:52:22.586"));
		turn4Pilot2.setPilot(pilot2);
		turn4Pilot2.setNumber(4);
		turn4Pilot2.setDuration(Duration.parse("PT1M4.010S"));
		turn4Pilot2.setTurnSpeedAverage(new BigDecimal(43.474f));

		Pilot pilot3 = new PilotMock(11, "S.VETTEL");
		TurnMock turn3Pilot3 = new TurnMock();
		turn3Pilot3.setTurnHour(LocalTime.parse("23:54:57.757"));
		turn3Pilot3.setPilot(pilot3);
		turn3Pilot3.setNumber(3);
		turn3Pilot3.setDuration(Duration.parse("PT1M18.097S"));
		turn3Pilot3.setTurnSpeedAverage(new BigDecimal(35.633f));

		turns = Arrays.asList(turn1Pilot1, turn2Pilot1, turn3Pilot1, turn4Pilot1, turn4Pilot2, turn3Pilot3);
		pilots = Arrays.asList(pilot1, pilot2, pilot3);

	}
}
