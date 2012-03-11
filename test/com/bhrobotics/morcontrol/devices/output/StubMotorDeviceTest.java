package com.bhrobotics.morcontrol.devices.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.StubMotorDevice;
import com.bhrobotics.morcontrol.support.TestCase;

public class StubMotorDeviceTest extends TestCase {
	@Test
	public void testModuleAndChannel() {
		StubMotorDevice device = new StubMotorDevice(2, 4);
		assertThat(device.getModule(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		StubMotorDevice device = new StubMotorDevice(2, 4);
		assertThat(device.getState(), is(0.0));

		assertThat(device.update(1.0), is(true));
		assertThat(device.getState(), is(1.0));
		
		assertThat(device.update(1.0), is(false));
		assertThat(device.getState(), is(1.0));
	}
	
	@Test(expected=InvalidStateException.class)
	public void testValueOutOfRange() {
		StubMotorDevice device = new StubMotorDevice(2, 4);
		device.update(3.0);
	}

	@Test
	public void testReset() {
		StubMotorDevice device = new StubMotorDevice(2, 4);
		device.update(1.0);
		device.reset();
		assertThat(device.getState(), is(0.0));
	}
}
