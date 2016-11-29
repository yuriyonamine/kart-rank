package br.com.yuriy.kart_rank.reader;

import java.util.List;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.Turn;

public interface LogReader {

	public List<Turn> findAllTurns();

	public List<Pilot> findAllPilots();

	public boolean isLoaded();
	
}
