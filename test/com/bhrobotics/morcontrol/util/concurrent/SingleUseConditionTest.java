package com.bhrobotics.morcontrol.util.concurrent;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class SingleUseConditionTest extends TestCase {
	private SingleUseCondition condition = new SingleUseCondition();
	private boolean done = false;
	
	@Test
	public void testAwait() {
		doAsync(new Runnable() {
			public void run() {
				condition.await();
				done = true;
			}
		});
		
		delay(5);
		condition.signal();
		delay(5);

		condition.await();
		assertThat(done, is(true));
	}
}
