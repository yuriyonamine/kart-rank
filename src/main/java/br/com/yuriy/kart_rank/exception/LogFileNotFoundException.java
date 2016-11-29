package br.com.yuriy.kart_rank.exception;

public class LogFileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 9079476193253944893L;

	public LogFileNotFoundException() {
		super();
	}

	public LogFileNotFoundException(String message) {
		super(message);
	}

}
