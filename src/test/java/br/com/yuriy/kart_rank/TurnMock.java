package br.com.yuriy.kart_rank;

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
		return null;
	}

	@Override
	public Pilot getPilot() {
		return null;
	}

	@Override
	public int getPilotNumber() {
		return 0;
	}

	@Override
	public int getNumber() {
		return 0;
	}

	@Override
	public Duration getDuration() {
		return null;
	}

	@Override
	public BigDecimal getTurnSpeedAverage() {
		// TODO Auto-generated method stub
		return null;
	}

}
