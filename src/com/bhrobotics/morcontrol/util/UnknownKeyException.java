package com.bhrobotics.morcontrol.util;

public class UnknownKeyException extends CausableRuntimeException {
	public UnknownKeyException() {
		super();
	}

	public UnknownKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownKeyException(String message) {
		super(message);
	}

	public UnknownKeyException(Throwable cause) {
		super(cause);
	}
}
