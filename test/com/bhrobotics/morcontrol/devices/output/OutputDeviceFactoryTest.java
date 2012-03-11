package com.bhrobotics.morcontrol.devices.output;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import com.bhrobotics.morcontrol.support.TestCase;

public class OutputDeviceFactoryTest extends TestCase {
	private OutputDeviceFactory factory = new OutputDeviceFactory();
	
	@Test
	public void testNewMotor() {
		MotorDevice device = factory.newMotor(1, 2);
		assertThat(device.getModule(), is(1));
		assertThat(device.getChannel(), is(2));
	}

	@Test
	public void testNewRelay() {
		RelayDevice device = factory.newRelay(1, 2);
		assertThat(device.getModule(), is(1));
		assertThat(device.getChannel(), is(2));
	}

	@Test
	public void testNewSolenoid() {
		SolenoidDevice device = factory.newSolenoid(1, 2);
		assertThat(device.getModule(), is(1));
		assertThat(device.getChannel(), is(2));
	}
}
