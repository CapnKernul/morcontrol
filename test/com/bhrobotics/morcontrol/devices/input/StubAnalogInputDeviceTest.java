package com.bhrobotics.morcontrol.devices.input;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class StubAnalogInputDeviceTest extends TestCase {
	private StubAnalogInputDevice device = new StubAnalogInputDevice(4, 2);
	
	@Test
	public void testGetAndSetState() {
		assertThat(device.getState(), is(0.0));
		device.setState(0.5);
		assertThat(device.getState(), is(0.5));
	}
	
	@Test
	public void testHasChanged() {
		assertThat(device.hasChanged(), is(false));
		device.setState(0.2);
		assertThat(device.hasChanged(), is(true));
		assertThat(device.hasChanged(), is(false));
		device.setState(0.2);
		assertThat(device.hasChanged(), is(false));
		device.setState(0.0);
		assertThat(device.hasChanged(), is(true));
		assertThat(device.hasChanged(), is(false));
	}
}
