package com.bhrobotics.morcontrol.devices;

import com.bhrobotics.morcontrol.util.CausableException;

public class InvalidStateException extends CausableException {

    private static final long serialVersionUID = -3116703525366305412L;

    public InvalidStateException() {
	super();
    }

    public InvalidStateException(String s) {
	super(s);
    }

    public InvalidStateException(Throwable cause) {
	super(cause);
    }

    public InvalidStateException(String s, Throwable cause) {
	super(s, cause);
    }
}
