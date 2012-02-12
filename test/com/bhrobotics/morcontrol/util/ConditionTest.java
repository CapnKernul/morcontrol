package com.bhrobotics.morcontrol.util;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.AssertionCounter;
import com.bhrobotics.morcontrol.support.TestCase;

public class ConditionTest extends TestCase {
	private Condition condition = new Condition();
	
	@Test
	public void testAwait() {
		final AssertionCounter counter = newCounter(1);
		doAsync(new Runnable() {
			public void run() {
				condition.await();
				counter.countDown();
			}
		});
		
		doAsync(new Runnable() {
			public void run() {
				delay(5);
				condition.signal();
			}
		});
		
		counter.await();
	}
}
