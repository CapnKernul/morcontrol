package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class StringMessageTest extends TestCase {
	private StringMessage message = new StringMessage("foo", "bar");

	@Test
	public void testKey() {
		assertThat(message.getKey(), is("foo"));
	}
	
	@Test
	public void testToString() {
		assertThat(message.toString(), is("bar"));
	}
}
