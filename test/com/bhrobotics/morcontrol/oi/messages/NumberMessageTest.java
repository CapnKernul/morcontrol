package com.bhrobotics.morcontrol.oi.messages;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class NumberMessageTest extends TestCase {
	private NumberMessage intMessage = new NumberMessage("foobar", 4);
	private NumberMessage doubleMessage = new NumberMessage("foobar", 5.4);
	
	@Test
	public void testKey() {
		assertThat(intMessage.getKey(), is("foobar"));
	}
	
	@Test
	public void testToDouble() {
		assertThat(intMessage.toDouble(), is(4.0));
		assertThat(doubleMessage.toDouble(), is(5.4));
	}
}
