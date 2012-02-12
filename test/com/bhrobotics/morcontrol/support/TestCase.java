package com.bhrobotics.morcontrol.support;

import org.junit.Assert;

public class TestCase extends Assert {
	public void doAsync(Runnable runnable) {
		AsynchronousUtils.doAsync(runnable);
	}
	
	public AssertionCounter newCounter(int count) {
		return new AssertionCounter(count);
	}
	
	public void delay(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			// Ignore.
		}
	}
}
