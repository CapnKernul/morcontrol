package com.bhrobotics.morcontrol.devices.input;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;
import static org.hamcrest.CoreMatchers.is;

public class InputDeviceAccessorTest extends TestCase {
	private InputDeviceAccessor accessor = new InputDeviceAccessor();
	
	@Test
	public void testGetDigitalModules() {
		assertThat(accessor.getDigitalModules(), is(new int[] { 2, 4 }));
	}

	@Test
	public void testGetAnalogModules() {
		assertThat(accessor.getAnalogModules(), is(new int[] { 1 }));
	}
	
	@Test
	public void testGetDigitalInputs() {
		DigitalInputDevice[] devices = accessor.getDigitalInputs();
		assertThat(devices.length, is(28));
		
		for (int i = 0; i < 14; i++) {
			DigitalInputDevice device = devices[i];
			assertThat(device.getModule(), is(2));
			assertThat(device.getChannel(), is(i + 1));
		}
		
		for (int i = 14; i < 28; i++) {
			DigitalInputDevice device = devices[i];
			assertThat(device.getModule(), is(4));
			assertThat(device.getChannel(), is(i - 13));
		}
	}

	@Test
	public void testGetAnalogInputs() {
		AnalogInputDevice[] devices = accessor.getAnalogInputs();
		assertThat(devices.length, is(8));
		
		for (int i = 0; i < 8; i++) {
			AnalogInputDevice device = devices[i];
			assertThat(device.getModule(), is(1));
			assertThat(device.getChannel(), is(i + 1));
		}
	}
}
