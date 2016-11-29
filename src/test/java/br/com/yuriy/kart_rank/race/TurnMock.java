package br.com.yuriy.kart_rank.race;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.Turn;

public class TurnMock implements Turn {

	private LocalTime turnHour;
	private Pilot pilot;
	private int number;
	private Duration duration;
	private BigDecimal turnSpeedAverage;

	public void setTurnHour(LocalTime turnHour) {
		this.turnHour = turnHour;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public void setNumber(int turnNumber) {
		this.number = turnNumber;
	}

	public void setDuration(Duration turnTime) {
		this.duration = turnTime;
	}

	public void setTurnSpeedAverage(BigDecimal turnAverage) {
		this.turnSpeedAverage = turnAverage;
	}

	@Override
	public LocalTime getTurnHour() {
		return this.turnHour;
	}

	@Override
	public Pilot getPilot() {
		return this.pilot;
	}

	@Override
	public int getPilotNumber() {
		return this.getPilotNumber();
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public Duration getDuration() {
		return this.duration;
	}

	@Override
	public BigDecimal getTurnSpeedAverage() {
		return this.turnSpeedAverage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + number;
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result + ((turnHour == null) ? 0 : turnHour.hashCode());
		result = prime * result + ((turnSpeedAverage == null) ? 0 : turnSpeedAverage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Turn))
			return false;
		Turn other = (Turn) obj;
		if (duration == null) {
			if (other.getDuration() != null)
				return false;
		} else if (!duration.equals(other.getDuration()))
			return false;
		if (number != other.getNumber())
			return false;
		if (pilot == null) {
			if (other.getPilot() != null)
				return false;
		} else if (!pilot.equals(other.getPilot()))
			return false;
		if (turnHour == null) {
			if (other.getTurnHour()!= null)
				return false;
		} else if (!turnHour.equals(other.getTurnHour()))
			return false;
		if (turnSpeedAverage == null) {
			if (other.getTurnSpeedAverage() != null)
				return false;
		} else if (!turnSpeedAverage.equals(other.getTurnSpeedAverage()))
			return false;
		return true;
	}

}
