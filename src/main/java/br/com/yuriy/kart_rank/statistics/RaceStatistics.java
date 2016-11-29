package br.com.yuriy.kart_rank.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.Race;
import br.com.yuriy.kart_rank.race.Turn;

public class RaceStatistics {

	private Race race;

	public RaceStatistics(Race race) {
		this.race = race;

	}

	public BigDecimal calculateSpeedRaceAverage(Pilot pilot) {
		BigDecimal speed = BigDecimal.ZERO;
		int turnsQuantity = 0;
		for (Turn turn : race.getTurns()) {
			if (turn.getPilot().equals(pilot)) {
				speed = speed.add(turn.getTurnSpeedAverage());
				turnsQuantity++;
			}
		}

		return speed.divide(new BigDecimal(turnsQuantity), 3,RoundingMode.FLOOR);

	}

	public Turn findBestRaceTurn() {
		Turn bestTurn = null;
		for (Turn turn : race.getTurns()) {
			if (bestTurn == null) {
				bestTurn = turn;
			}

			if (turn.getDuration().toNanos() < bestTurn.getDuration().toNanos()) {
				bestTurn = turn;
			}
		}

		return bestTurn;
	}

	public Turn findBestPilotTurn(Pilot pilot) {
		Turn bestTurn = null;
		for (Turn turn : race.getTurns()) {
			if (!turn.getPilot().equals(pilot)) {
				continue;
			}

			if (bestTurn == null) {
				bestTurn = turn;
			}

			if (turn.getDuration().toNanos() < bestTurn.getDuration().toNanos()) {
				bestTurn = turn;
			}
		}

		return bestTurn;
	}

}
