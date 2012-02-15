package com.bhrobotics.morcontrol.support;

import org.junit.Assert;

public class TestCase extends Assert {
	public void doAsync(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public void doAsync(String name, Runnable runnable) {
		Thread thread = new Thread(runnable, name);
		thread.start();
	}
	
	public AssertionCounter newCounter(int count) {
		return new AssertionCounter(count);
	}
	
	public void delay(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			System.err.println("Delay interrupted! " + e.getMessage());
		}
	}
}
