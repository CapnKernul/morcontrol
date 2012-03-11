package com.bhrobotics.morcontrol.devices.output;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.bhrobotics.morcontrol.devices.output.StubRelayDevice;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.support.TestCase;

public class StubRelayDeviceTest extends TestCase {
	@Test
	public void testModuleAndChannel() {
		StubRelayDevice device = new StubRelayDevice(2, 4);
		assertThat(device.getModule(), is(2));
		assertThat(device.getChannel(), is(4));
	}
	
	@Test
	public void testState() {
		StubRelayDevice device = new StubRelayDevice(2, 4);
		assertThat(device.getState(), is(RelayState.OFF));

		RelayState newState = RelayState.FORWARD;
		assertThat(device.update(newState), is(true));
		assertThat(device.getState(), is(newState));
		
		assertThat(device.update(newState), is(false));
		assertThat(device.getState(), is(newState));
	}

	@Test
	public void testReset() {
		StubRelayDevice device = new StubRelayDevice(2, 4);
		device.update(RelayState.FORWARD);
		device.reset();
		assertThat(device.getState(), is(RelayState.OFF));
	}
}
