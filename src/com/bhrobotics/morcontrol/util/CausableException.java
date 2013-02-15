package com.bhrobotics.morcontrol.util;

public abstract class CausableException extends Exception {
	private Throwable cause;

	public CausableException() {
		super();
	}

	public CausableException(String message) {
		super(message);
	}

	public CausableException(Throwable cause) {
		super();
		this.cause = cause;
	}

	public CausableException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}
}