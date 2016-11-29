import java.io.File;
import java.time.Duration;

import br.com.yuriy.kart_rank.race.Pilot;
import br.com.yuriy.kart_rank.race.Race;
import br.com.yuriy.kart_rank.reader.LogReaderImp;
import br.com.yuriy.kart_rank.statistics.RaceStatistics;

public class main {

	public static void main(String[] args) {
		File logFile = new File("C:\\Users\\Play\\Desktop\\log.txt");
		LogReaderImp logReader = new LogReaderImp(logFile);

		Race race = new Race(logReader.findAllTurns(), logReader.findAllPilots());
		RaceStatistics raceStatistics = new RaceStatistics(race);

		Duration raceDuration = race.calculateRaceDuration();
		String durationSeconds = raceDuration.minus(Duration.ofMinutes(raceDuration.toMinutes())).getSeconds() + "."
				+ raceDuration.minus(Duration.ofMinutes(raceDuration.toMinutes())).getNano() / 1000 / 1000;

		System.out.println("--------------------------------------------------------------");

		System.out.println(String.format("A duração da prova foi de: %d horas, %d minutos, %s segundos",
				raceDuration.toHours(), raceDuration.toMinutes(), durationSeconds));

		System.out.println("--------------------------------------------------------------\n");

		for (Pilot pilot : race.getRankedPilots()) {
			int position = race.findArrivalPosition(pilot);
			int pilotNumber = pilot.getNumber();
			String pilotName = pilot.getName();
			int turnsQuantity = race.findCompletedTurnsQuantity(pilot);
			
			System.out.println("Posição do piloto: " + position);
			System.out.println("Número do piloto: " + pilotNumber);
			System.out.println("Nome do piloto: " + pilotName);
			System.out.println("Quantidade de Voltas Completadas: " + turnsQuantity);
			System.out.println("Melhor volta do piloto: " + raceStatistics.findBestPilotTurn(pilot).getNumber());
			System.out.println("Velocidade média do piloto durante a prova: " + raceStatistics.calculateSpeedRaceAverage(pilot));
		
			System.out.println(String.format("A duração da prova foi de: %d horas, %d minutos, %s segundos",
					raceDuration.toHours(), raceDuration.toMinutes(), durationSeconds));

			Duration arrivalTimeAfterWinner = race.calculateDistanceFromPilotAndWinner(pilot);
			String secondsOfArrivalTimeAfterWinner = arrivalTimeAfterWinner.minus(Duration.ofMinutes(arrivalTimeAfterWinner.toMinutes())).getSeconds() + "."
					+ arrivalTimeAfterWinner.minus(Duration.ofMinutes(arrivalTimeAfterWinner.toMinutes())).getNano() / 1000 / 1000;


			System.out.println(String.format("A diferença de tempo entre a chegada do primeiro colocado e o piloto %s foi de %d horas, %d minutos, %s segundos",
					pilotName, arrivalTimeAfterWinner.toHours(), arrivalTimeAfterWinner.toMinutes(), secondsOfArrivalTimeAfterWinner));

			System.out.println("______________________________________________________________");
		}
	}

}
