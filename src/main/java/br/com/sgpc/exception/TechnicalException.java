package br.com.sgpc.exception;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 2405447095231655071L;
	
	public TechnicalException(final Exception e) {
		super( e );
	}
	
	public TechnicalException(final String message) {
		super( message );
	}
}
