package br.com.yuriy.kart_rank.race;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class TurnImp implements Turn {

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

	public BigDecimal getTurnSpeedAverage() {
		return turnSpeedAverage;
	}

	public LocalTime getTurnHour() {
		return turnHour;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public int getNumber() {
		return this.number;
	}

	public Duration getDuration() {
		return duration;
	}

	public int getPilotNumber() {
		return pilot.getNumber();
	}

	public String getName() {
		return pilot.getName();
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
		if (getClass() != obj.getClass())
			return false;
		TurnImp other = (TurnImp) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (number != other.number)
			return false;
		if (pilot == null) {
			if (other.pilot != null)
				return false;
		} else if (!pilot.equals(other.pilot))
			return false;
		if (turnHour == null) {
			if (other.turnHour != null)
				return false;
		} else if (!turnHour.equals(other.turnHour))
			return false;
		if (turnSpeedAverage == null) {
			if (other.turnSpeedAverage != null)
				return false;
		} else if (!turnSpeedAverage.equals(other.turnSpeedAverage))
			return false;
		return true;
	}
}
