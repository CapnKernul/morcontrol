package com.bhrobotics.morcontrol.output;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.bhrobotics.morcontrol.output.RelayDevice;
import com.bhrobotics.morcontrol.output.RelayState;
import com.bhrobotics.morcontrol.support.TestCase;

public class RelayDeviceTest extends TestCase {
	@Test
	public void testSlotAndChannel() {
		RelayDevice device = new RelayDevice(2, 4);
		assertThat(device.getSlot(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		RelayDevice device = new RelayDevice(2, 4);
		assertThat(device.getState(), is(RelayState.OFF));

		RelayState newState = RelayState.FORWARD;
		assertThat(device.update(newState), is(true));
		assertThat(device.getState(), is(newState));
		
		assertThat(device.update(newState), is(false));
		assertThat(device.getState(), is(newState));
	}

	@Test
	public void testReset() {
		RelayDevice device = new RelayDevice(2, 4);
		device.update(RelayState.FORWARD);
		device.reset();
		assertThat(device.getState(), is(RelayState.OFF));
	}
}
