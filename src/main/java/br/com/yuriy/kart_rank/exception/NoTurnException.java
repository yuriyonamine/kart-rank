package br.com.yuriy.kart_rank.exception;

public class NoTurnException extends RuntimeException {

	private static final long serialVersionUID = 6859960403010423096L;

	public NoTurnException() {
		super();
	}

	public NoTurnException(String message) {
		super(message);
	}

}
