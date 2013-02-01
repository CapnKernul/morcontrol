package com.bhrobotics.morcontrol.io;

import com.bhrobotics.morcontrol.util.CausableRuntimeException;

public class RuntimeIOException extends CausableRuntimeException {
    
    private static final long serialVersionUID = -5955257532934551617L;

    public RuntimeIOException() {
	super();
    }

    public RuntimeIOException(String message, Throwable cause) {
	super(message, cause);
    }

    public RuntimeIOException(String message) {
	super(message);
    }

    public RuntimeIOException(Throwable cause) {
	super(cause);
    }
}
