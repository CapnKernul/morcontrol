package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.util.CausableRuntimeException;

public class InvalidMessageException extends CausableRuntimeException {
	public InvalidMessageException() {
		super();
	}

	public InvalidMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMessageException(String message) {
		super(message);
	}

	public InvalidMessageException(Throwable cause) {
		super(cause);
	}
}
