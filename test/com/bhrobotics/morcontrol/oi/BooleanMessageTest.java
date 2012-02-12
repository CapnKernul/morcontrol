package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class BooleanMessageTest extends TestCase {
	private BooleanMessage message = new BooleanMessage("foobar", true);

	@Test
	public void testKey() {
		assertThat(message.getKey(), is("foobar"));
	}
	
	@Test
	public void testToBoolean() {
		assertThat(message.toBoolean(), is(true));
	}
}
