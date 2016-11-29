package br.com.yuriy.kart_rank.race;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.yuriy.kart_rank.exception.UnfinishedRaceException;
import br.com.yuriy.kart_rank.exception.PilotNotRegisteredInRaceException;

public class Race {

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

	public Race(List<Turn> turns, List<Pilot> pilots) {
		this.turns = new ArrayList<Turn>(turns);
		this.rankedPilots = new ArrayList<Pilot>(pilots);
		this.turns.sort(ascTurnNumberDescTurnTimeComparator);

	}

	public int findArrivalPosition(Pilot pilot) {
		int pilotRankPosition = 0;

		if (!rankedPilots.contains(pilot)) {
			throw new PilotNotRegisteredInRaceException("Esse piloto não está cadastrado nessa corrida.");
		}

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
		if (!rankedPilots.contains(pilot)) {
			throw new PilotNotRegisteredInRaceException("Esse piloto não está cadastrado nessa corrida.");
		}

		return findLastPilotTurn(pilot).getNumber();
	}

	public Turn findLastPilotTurn(Pilot pilot) {
		Turn lastTurn = null;
		for (int index = turns.size() - 1; index >= 0; index--) {
			Turn turn = turns.get(index);

			if (turn.getPilot().equals(pilot)) {
				lastTurn = turn;
				break;
			}
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

	private Turn findFirstTurn() {
		return turns.get(0);
	}

	private Turn findWinnerTurn() {
		Turn winnerTurn = null;
		for (Turn turn : turns) {
			if (turn.getNumber() == Race.MAX_TURNS) {
				winnerTurn = turn;
				break;
			}
		}

		if (winnerTurn == null) {
			throw new UnfinishedRaceException("Ninguém completou a prova.");
		}

		return winnerTurn;
	}

	public List<Turn> getTurns() {
		return Collections.unmodifiableList(turns);
	}

	public Duration calculateDistanceFromPilotAndWinner(Pilot pilot) {
		Turn winnerTurn = findWinnerTurn();
		Turn lastPilotTurn = findLastPilotTurn(pilot);

		return Duration.between(winnerTurn.getTurnHour(), lastPilotTurn.getTurnHour());
	}

	public List<Pilot> getRankedPilots() {
		return Collections.unmodifiableList(rankedPilots);
	}

}
