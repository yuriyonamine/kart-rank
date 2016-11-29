package br.com.yuriy.kart_rank;

import br.com.yuriy.kart_rank.race.Pilot;

public class PilotMock implements Pilot {

	private String name;
	private int number;

	public PilotMock(int number, String name) {
		this.number = number;
		this.name = name;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public String getName() {
		return name;
	}

}
