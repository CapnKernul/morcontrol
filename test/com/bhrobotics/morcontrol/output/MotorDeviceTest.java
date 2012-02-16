package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.support.TestCase;

public class MotorDeviceTest extends TestCase {
	@Test
	public void testSlotAndChannel() {
		MotorDevice device = new MotorDevice(2, 4);
		assertThat(device.getSlot(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		MotorDevice device = new MotorDevice(2, 4);
		assertThat(device.getState(), is(new AnalogState(0.0)));

		AnalogState newState = new AnalogState(1.0);
		assertThat(device.update(newState), is(true));
		assertThat(device.getState(), is(newState));
		
		assertThat(device.update(newState), is(false));
		assertThat(device.getState(), is(newState));
	}

	@Test
	public void testReset() {
		MotorDevice device = new MotorDevice(2, 4);
		device.update(new AnalogState(1.0));
		device.reset();
		assertThat(device.getState(), is(new AnalogState(0.0)));
	}
}
