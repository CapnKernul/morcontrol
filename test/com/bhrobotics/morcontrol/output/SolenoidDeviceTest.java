package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import com.bhrobotics.morcontrol.output.DigitalState;
import com.bhrobotics.morcontrol.output.SolenoidDevice;
import com.bhrobotics.morcontrol.support.TestCase;
import static org.hamcrest.CoreMatchers.is;

public class SolenoidDeviceTest extends TestCase {
	@Test
	public void testSlotAndChannel() {
		SolenoidDevice device = new SolenoidDevice(2, 4);
		assertThat(device.getSlot(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		SolenoidDevice device = new SolenoidDevice(2, 4);
		assertThat(device.getState(), is(DigitalState.OFF));

		assertThat(device.update(DigitalState.ON), is(true));
		assertThat(device.getState(), is(DigitalState.ON));
		
		assertThat(device.update(DigitalState.ON), is(false));
		assertThat(device.getState(), is(DigitalState.ON));
	}

	@Test
	public void testReset() {
		SolenoidDevice device = new SolenoidDevice(2, 4);
		device.update(DigitalState.ON);
		device.reset();
		assertThat(device.getState(), is(DigitalState.OFF));
	}
}
