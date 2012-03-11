package com.bhrobotics.morcontrol.devices.input;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

import static org.hamcrest.CoreMatchers.is;

public class StubDigitalInputDeviceTest extends TestCase {
	private StubDigitalInputDevice device = new StubDigitalInputDevice(4, 2);
	
	@Test
	public void testGetAndSetState() {
		assertThat(device.getState(), is(false));
		device.setState(true);
		assertThat(device.getState(), is(true));
	}
	
	@Test
	public void testHasChanged() {
		assertThat(device.hasChanged(), is(false));
		device.setState(true);
		assertThat(device.hasChanged(), is(true));
		assertThat(device.hasChanged(), is(false));
		device.setState(true);
		assertThat(device.hasChanged(), is(false));
		device.setState(false);
		assertThat(device.hasChanged(), is(true));
		assertThat(device.hasChanged(), is(false));
	}
}
