package com.bhrobotics.morcontrol.util.concurrent;

import com.bhrobotics.morcontrol.util.CausableRuntimeException;

public class IllegalMutexUnlocking extends CausableRuntimeException {
	public IllegalMutexUnlocking() {
		super();
	}

	public IllegalMutexUnlocking(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalMutexUnlocking(String message) {
		super(message);
	}

	public IllegalMutexUnlocking(Throwable cause) {
		super(cause);
	}
}
