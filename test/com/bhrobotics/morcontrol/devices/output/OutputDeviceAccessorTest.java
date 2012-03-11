package com.bhrobotics.morcontrol.devices.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.devices.output.OutputDeviceAccessor;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.support.TestCase;

public class OutputDeviceAccessorTest extends TestCase {
	private OutputDeviceAccessor accessor = new OutputDeviceAccessor();
	
	@Test
	public void testGetMotor() {
		MotorDevice device = accessor.getMotor(4, 1);
		assertThat(device.getModule(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateMotor() {
		accessor.updateMotor(4, 1, 0.5);
		MotorDevice device = accessor.getMotor(4, 1);
		assertThat(device.getState(), is(0.5));
	}

	@Test
	public void testGetSolenoid() {
		SolenoidDevice device = accessor.getSolenoid(4, 1);
		assertThat(device.getModule(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateSolenoid() {
		accessor.updateSolenoid(4, 1, true);
		SolenoidDevice device = accessor.getSolenoid(4, 1);
		assertThat(device.getState(), is(true));
	}

	@Test
	public void testGetRelay() {
		RelayDevice device = accessor.getRelay(4, 1);
		assertThat(device.getModule(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateRelay() {
		accessor.updateRelay(4, 1, RelayState.FORWARD);
		RelayDevice device = accessor.getRelay(4, 1);
		assertThat(device.getState(), is(RelayState.FORWARD));
	}
	
	@Test
	public void testReset() {
		accessor.updateMotor(4, 1, 0.2);
		accessor.updateMotor(4, 3, 0.0);
		accessor.updateRelay(1, 1, RelayState.FORWARD);
		accessor.updateRelay(3, 1, RelayState.REVERSE);
		accessor.updateSolenoid(4, 1, true);
		accessor.updateSolenoid(2, 4, false);
		accessor.reset();
		
		MotorDevice motor1 = accessor.getMotor(4, 1);
		MotorDevice motor2 = accessor.getMotor(4, 3);
		RelayDevice relay1 = accessor.getRelay(1, 1);
		RelayDevice relay2 = accessor.getRelay(3, 1);
		SolenoidDevice solenoid1 = accessor.getSolenoid(4, 1);
		SolenoidDevice solenoid2 = accessor.getSolenoid(2, 4);
		
		assertThat(motor1.getState(), is(0.0));
		assertThat(motor2.getState(), is(0.0));
		assertThat(relay1.getState(), is(RelayState.OFF));
		assertThat(relay2.getState(), is(RelayState.OFF));
		assertThat(solenoid1.getState(), is(false));
		assertThat(solenoid2.getState(), is(false));
	}
}
