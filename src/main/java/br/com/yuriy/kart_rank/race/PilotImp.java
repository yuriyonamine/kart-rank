package br.com.yuriy.kart_rank.race;

public class PilotImp implements Pilot{

	private int number;
	private String name;

	public PilotImp(int pilotNumber, String pilotName) {
		this.number = pilotNumber;
		this.name = pilotName;
	}

	public int getNumber() {
		return number;
	}

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
		if (getClass() != obj.getClass())
			return false;
		PilotImp other = (PilotImp) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
}
