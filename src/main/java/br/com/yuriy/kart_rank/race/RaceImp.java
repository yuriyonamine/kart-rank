package br.com.yuriy.kart_rank.race;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.yuriy.kart_rank.exception.NoTurnException;
import br.com.yuriy.kart_rank.exception.PilotNotRegisteredInRaceException;
import br.com.yuriy.kart_rank.exception.UnfinishedRaceException;

public class RaceImp implements Race {

	private static final int MAX_TURNS = 4;
	private List<Turn> turns;
	private List<Pilot> rankedPilots;
	private Comparator<Turn> ascTurnNumberDescTurnTimeComparator = (turn1, turn2) -> {
		int comparationInt = Integer.compare(turn1.getNumber(), turn2.getNumber());
		if (comparationInt == 0) {
			comparationInt = turn1.getTurnHour().compareTo(turn2.getTurnHour());
		}

		return comparationInt;
	};

	public RaceImp(List<Turn> turns, List<Pilot> pilots) {
		this.turns = new ArrayList<Turn>(turns);
		this.rankedPilots = new ArrayList<Pilot>(pilots);
		this.turns.sort(ascTurnNumberDescTurnTimeComparator);

	}

	public int findArrivalPosition(Pilot pilot) {
		int pilotRankPosition = 0;

		verifyValidPilot(pilot);

		for (Pilot rankedPilot : rankedPilots) {
			if (rankedPilot.equals(pilot)) {
				pilotRankPosition++;
				break;
			}

			pilotRankPosition++;
		}

		return pilotRankPosition;
	}

	public int findCompletedTurnsQuantity(Pilot pilot) {
		verifyValidPilot(pilot);

		return findLastPilotTurn(pilot).getNumber();
	}

	public Turn findLastPilotTurn(Pilot pilot) {
		verifyValidPilot(pilot);

		Turn lastTurn = null;
		for (int index = turns.size() - 1; index >= 0; index--) {
			Turn turn = turns.get(index);

			if (turn.getPilot().equals(pilot)) {
				lastTurn = turn;
				break;
			}
		}

		if (lastTurn == null) {
			throw new NoTurnException("Não existe nenhuma volta registrada para esse piloto.");
		}

		return lastTurn;
	}

	public Duration calculateRaceDuration() {
		Turn firstTurn = findFirstTurn();
		Turn lastTurn = findWinnerTurn();

		LocalTime startTime = firstTurn.getTurnHour().minus(firstTurn.getDuration());
		LocalTime endTime = lastTurn.getTurnHour();

		Duration duration = Duration.between(startTime, endTime);

		return duration;
	}

	public Duration calculateDistanceBetweenPilots(Pilot pilot1, Pilot pilot2) {
		Turn winnerTurn = findLastPilotTurn(pilot1);
		Turn lastPilotTurn = findLastPilotTurn(pilot2);

		return Duration.between(winnerTurn.getTurnHour(), lastPilotTurn.getTurnHour()).abs();
	}

	private Turn findFirstTurn() {
		return turns.get(0);
	}

	private Turn findWinnerTurn() {
		Turn winnerTurn = null;
		for (Turn turn : turns) {
			if (turn.getNumber() == RaceImp.MAX_TURNS) {
				winnerTurn = turn;
				break;
			}
		}

		if (winnerTurn == null) {
			throw new UnfinishedRaceException("Ninguém completou a prova.");
		}

		return winnerTurn;
	}

	public Pilot findWinnerPilot() {
		return findWinnerTurn().getPilot();
	}

	private void verifyValidPilot(Pilot pilot) {
		if (!rankedPilots.contains(pilot)) {
			throw new PilotNotRegisteredInRaceException("Esse piloto não está cadastrado nessa corrida.");
		}
	}

	public List<Turn> getTurns() {
		return Collections.unmodifiableList(turns);
	}

	public List<Pilot> getRankedPilots() {
		return Collections.unmodifiableList(rankedPilots);
	}

}
