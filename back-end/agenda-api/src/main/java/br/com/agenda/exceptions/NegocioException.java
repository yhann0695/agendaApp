package br.com.agenda.exceptions;

public class NegocioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -743073900374346726L;

	public NegocioException(String msg) {
		super(msg);
	}
}
