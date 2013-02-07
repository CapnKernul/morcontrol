package com.bhrobotics.morcontrol.util;

public abstract class CausableRuntimeException extends RuntimeException {
	private Throwable cause;

	public CausableRuntimeException() {
		super();
	}

	public CausableRuntimeException(String message) {
		super(message);
	}

	public CausableRuntimeException(Throwable cause) {
		super();
		this.cause = cause;
	}

	public CausableRuntimeException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	public Throwable getCause() {
		return cause;
	}
}
