package com.bhrobotics.morcontrol.util;

public class UnsupportedOperationException extends CausableRuntimeException {
    public UnsupportedOperationException() {
    }

    public UnsupportedOperationException(String message) {
	super(message);
    }

    public UnsupportedOperationException(Throwable cause) {
	super(cause);
    }

    public UnsupportedOperationException(String message, Throwable cause) {
	super(message, cause);
    }
}
