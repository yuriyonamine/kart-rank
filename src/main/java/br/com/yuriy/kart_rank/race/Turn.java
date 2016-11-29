package br.com.yuriy.kart_rank.race;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public interface Turn {

	public LocalTime getTurnHour();

	public Pilot getPilot();

	public int getPilotNumber();

	public int getNumber();

	public Duration getDuration();

	public BigDecimal getTurnSpeedAverage();
	
}
