package br.com.yuriy.kart_rank.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.PilotImp;
import br.com.yuriy.kart_rank.race.Turn;
import br.com.yuriy.kart_rank.race.TurnImp;

public class LogReaderImp implements LogReader{
	private File logFile;
	private List<Turn> turns;
	private List<Pilot> pilots;

	public LogReaderImp(File logFile) {
		this.logFile = logFile;
		turns = new ArrayList<>();
		pilots = new ArrayList<>();

		try (Scanner scanner = new Scanner(logFile)) {
			boolean isHeaderRead = false;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (!isHeaderRead) {
					isHeaderRead = true;
					continue;
				}

				String[] splittedLine = line.split(" +");
				Turn turn = createTurn(splittedLine);
				
				turns.add(turn);
				
				if (!pilots.contains(turn.getPilot())) {
					pilots.add(turn.getPilot());
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Turn> findAllTurns() {
		return Collections.unmodifiableList(turns);
	}

	public List<Pilot> findAllPilots() {
		return Collections.unmodifiableList(pilots);
	}

	private Turn createTurn(String[] line) {
		int pilotNumber = Integer.parseInt(line[1]);
		String pilotName = line[3];
		Pilot pilot = new PilotImp(pilotNumber, pilotName);

		TurnImp turn = new TurnImp();
		turn.setTurnHour(LocalTime.parse(line[0]));
		turn.setPilot(pilot);
		turn.setNumber(Integer.parseInt(line[4]));
		turn.setDuration(createTurnTime(line[5]));
		turn.setTurnSpeedAverage(formatTurnAverage(line[6]));

		return turn;
	}

	private BigDecimal formatTurnAverage(String turnAverageString) {
		return new BigDecimal(turnAverageString.replace(",", "."));
	}

	private Duration createTurnTime(String turnTimeString) {
		String[] turnTime = turnTimeString.split(":");
		return Duration.parse("PT" + turnTime[0] + "M" + turnTime[1] + "S");
	}

	@Override
	public boolean isLoaded() {
		return logFile.exists();
	}
}
