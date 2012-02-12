package com.bhrobotics.morcontrol.util;

public class UnsupportedOperationException extends CausableRuntimeException {
	public UnsupportedOperationException() {
		super();
	}

	public UnsupportedOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedOperationException(String message) {
		super(message);
	}

	public UnsupportedOperationException(Throwable cause) {
		super(cause);
	}
}
