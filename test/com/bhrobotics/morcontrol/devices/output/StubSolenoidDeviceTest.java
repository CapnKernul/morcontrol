package com.bhrobotics.morcontrol.devices.output;

import org.junit.Test;

import com.bhrobotics.morcontrol.devices.output.StubSolenoidDevice;
import com.bhrobotics.morcontrol.support.TestCase;
import static org.hamcrest.CoreMatchers.is;

public class StubSolenoidDeviceTest extends TestCase {
	@Test
	public void testModuleAndChannel() {
		StubSolenoidDevice device = new StubSolenoidDevice(2, 4);
		assertThat(device.getModule(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		StubSolenoidDevice device = new StubSolenoidDevice(2, 4);
		assertThat(device.getState(), is(false));

		assertThat(device.update(true), is(true));
		assertThat(device.getState(), is(true));
		
		assertThat(device.update(true), is(false));
		assertThat(device.getState(), is(true));
	}

	@Test
	public void testReset() {
		StubSolenoidDevice device = new StubSolenoidDevice(2, 4);
		device.update(true);
		device.reset();
		assertThat(device.getState(), is(false));
	}
}
