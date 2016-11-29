package br.com.yuriy.kart_rank.race;

import java.time.Duration;
import java.util.List;

public interface Race {

	public int findArrivalPosition(Pilot pilot);

	public Duration calculateRaceDuration();

	public Pilot findWinnerPilot();

	public Duration calculateDistanceBetweenPilots(Pilot pilot1, Pilot pilot2);

	public List<Turn> getTurns();

	public List<Pilot> getRankedPilots();

	public int findCompletedTurnsQuantity(Pilot pilot);

}
