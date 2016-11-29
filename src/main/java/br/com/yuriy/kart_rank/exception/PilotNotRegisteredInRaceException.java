package br.com.yuriy.kart_rank.exception;

public class PilotNotRegisteredInRaceException extends RuntimeException {

	private static final long serialVersionUID = 6273542688660696541L;

	public PilotNotRegisteredInRaceException() {
	}

	public PilotNotRegisteredInRaceException(String message) {
		super(message);
	}
}
