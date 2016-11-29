package br.com.yuriy.kart_rank.race;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pilot))
			return false;
		Pilot other = (Pilot) obj;
		if (number != other.getNumber())
			return false;
		return true;
	}
	
	
}
