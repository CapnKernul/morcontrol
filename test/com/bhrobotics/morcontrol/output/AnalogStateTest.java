package com.bhrobotics.morcontrol.output;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.output.InvalidStateException;
import com.bhrobotics.morcontrol.support.TestCase;

public class AnalogStateTest extends TestCase {
	@Test
	public void testValue() {
		AnalogState state = new AnalogState(0.2);
		assertThat(state.toDouble(), is(0.2));
	}
	
	@Test(expected=InvalidStateException.class)
	public void testInvalidValue() {
		new AnalogState(3.2);
	}
	
	@Test
	public void testEquals() {
		AnalogState state1 = new AnalogState(0.0);
		AnalogState state2 = new AnalogState(1.0);
		AnalogState state3 = new AnalogState(1.0);
		
		assertFalse(state1.equals(state2));
		assertFalse(state1.equals(state3));
		assertEquals(state2, state3);
	}
}
