package com.sri.exception;

public class InvalidException extends RuntimeException {

	/**
	 * Customized exception for invalid input
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException() {
	}

	public InvalidException(String message) {
		super(message);
	}

	public InvalidException(Throwable cause) {
		super(cause);
	}

	public InvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
