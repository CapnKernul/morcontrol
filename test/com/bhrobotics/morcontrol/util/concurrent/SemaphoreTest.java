package com.bhrobotics.morcontrol.util.concurrent;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.AssertionCounter;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.concurrent.Semaphore;

public class SemaphoreTest extends TestCase {
	private Semaphore semaphore = new Semaphore();
	
	@Test
	public void testSemaphore() {
		doAsync(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					semaphore.release();
				}
			}
		});
		
		final AssertionCounter counter = newCounter(50);
		doAsync(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					semaphore.acquire();
					counter.countDown();
				}
			}
		});
		
		counter.await();
	}
}
