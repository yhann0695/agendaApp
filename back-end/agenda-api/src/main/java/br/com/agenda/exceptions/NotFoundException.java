package br.com.agenda.exceptions;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1737141549404780353L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
