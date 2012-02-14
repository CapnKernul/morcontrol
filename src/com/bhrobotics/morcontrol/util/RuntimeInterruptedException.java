package com.bhrobotics.morcontrol.util;

public class RuntimeInterruptedException extends CausableRuntimeException {
	public RuntimeInterruptedException() {
		super();
	}

	public RuntimeInterruptedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeInterruptedException(String message) {
		super(message);
	}

	public RuntimeInterruptedException(Throwable cause) {
		super(cause);
	}
}
