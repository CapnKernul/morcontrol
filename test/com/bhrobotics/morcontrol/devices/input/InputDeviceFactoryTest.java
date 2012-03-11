package com.bhrobotics.morcontrol.devices.input;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;
import static org.hamcrest.CoreMatchers.is;

public class InputDeviceFactoryTest extends TestCase {
	private InputDeviceFactory factory = new InputDeviceFactory();
	
	@Test
	public void testNewDigitalInput() {
		DigitalInputDevice device = factory.newDigitalInput(2, 3);
		assertThat(device.getModule(), is(2));
		assertThat(device.getChannel(), is(3));
	}
}
