package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.CausableRuntimeException;

public class OIException extends CausableRuntimeException {
	public OIException() {
	}

	public OIException(String message) {
		super(message);
	}

	public OIException(Throwable cause) {
		super(cause);
	}

	public OIException(String message, Throwable cause) {
		super(message, cause);
	}
}
