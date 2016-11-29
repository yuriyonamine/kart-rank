package br.com.yuriy.kart_rank.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.yuriy.kart_rank.exception.LogFileNotFoundException;

public class LogReaderTest {
	private File logFile;

	@Before
	public void initialize() throws FileNotFoundException {
		logFile = createLogFile();
	}

	@Test
	public void loadLogFile() {
		LogReader reader = new LogReaderImp(logFile);

		assertTrue(reader.isLoaded());

	}

	@Test(expected = LogFileNotFoundException.class)
	public void inexistentLogFile() throws IOException {
		File emptyLogFile = new File("inexistent.txt");

		LogReader reader = new LogReaderImp(emptyLogFile);
	}

	@Test
	public void findAllTurns() throws FileNotFoundException {
		LogReaderImp reader = new LogReaderImp(logFile);

		assertEquals(6, reader.findAllTurns().size());
	}

	private File createLogFile() throws FileNotFoundException {
		File logFile = new File("logFileTest.txt");
		PrintWriter print = new PrintWriter(logFile);
		print.println("Hora                   Piloto         Nº Volta   Tempo Volta      Velocidade média da volta");
		print.println("23:49:08.277      038 – F.MASSA             1     1:02.852            44,275");
		print.println("23:49:10.858      033 – R.BARRICHELLO       1     1:04.352            43,243");
		print.println("23:52:01.796      011 – S.VETTEL            1     3:31.315            13,169");
		print.println("23:50:11.447      038 – F.MASSA             2     1:03.170            44,053");
		print.println("23:51:14.216      038 – F.MASSA             3     1:02.769            44,334");
		print.println("23:52:17.003      038 – F.MASS              4     1:02.787            44,321");

		print.close();

		return logFile;
	}

	@After
	public void dispose() {
		logFile.delete();
	}
}
