package br.com.yuriy.kart_rank;

import java.util.List;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.Turn;
import br.com.yuriy.kart_rank.reader.LogReader;

public class LogReaderMock implements LogReader {

	private List<Turn> turns;
	private List<Pilot> pilots;

	public LogReaderMock() {
	
	}

	@Override
	public List<Turn> findAllTurns() {
		return turns;
	}

	@Override
	public List<Pilot> findAllPilots() {
		return pilots;
	}

	@Override
	public boolean isLoaded() {
		return false;
	}

}
