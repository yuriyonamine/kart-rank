package br.com.yuriy.kart_rank.exception;

public class UnfinishedRaceException extends RuntimeException {
	
	private static final long serialVersionUID = 5419287213405763763L;

	public UnfinishedRaceException() {
		super();
	}

	public UnfinishedRaceException(String message) {
		super(message);
	}

}
