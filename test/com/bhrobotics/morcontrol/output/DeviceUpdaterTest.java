package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import com.bhrobotics.morcontrol.support.TestCase;

public class DeviceUpdaterTest extends TestCase {
	private DeviceUpdater updater = new DeviceUpdater();
	
	@Test
	public void testGetMotor() {
		MotorDevice device = updater.getMotor(4, 1);
		assertThat(device.getSlot(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateMotor() {
		AnalogState state = new AnalogState(0.5);
		updater.updateMotor(4, 1, state);
		
		MotorDevice device = updater.getMotor(4, 1);
		assertThat(device.getState(), is(state));
	}

	@Test
	public void testGetSolenoid() {
		SolenoidDevice device = updater.getSolenoid(4, 1);
		assertThat(device.getSlot(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateSolenoid() {
		updater.updateSolenoid(4, 1, DigitalState.ON);
		
		SolenoidDevice device = updater.getSolenoid(4, 1);
		assertThat(device.getState(), is(DigitalState.ON));
	}

	@Test
	public void testGetRelay() {
		RelayDevice device = updater.getRelay(4, 1);
		assertThat(device.getSlot(), is(4));
		assertThat(device.getChannel(), is(1));
	}
	
	@Test
	public void testUpdateRelay() {
		updater.updateRelay(4, 1, RelayState.FORWARD);
		
		RelayDevice device = updater.getRelay(4, 1);
		assertThat(device.getState(), is(RelayState.FORWARD));
	}
	
	@Test
	public void testReset() {
		updater.updateMotor(4, 1, new AnalogState(0.2));
		updater.updateMotor(4, 3, new AnalogState(0.0));
		updater.updateRelay(1, 1, RelayState.FORWARD);
		updater.updateRelay(3, 1, RelayState.REVERSE);
		updater.updateSolenoid(4, 1, DigitalState.ON);
		updater.updateSolenoid(2, 4, DigitalState.OFF);
		updater.reset();
		
		MotorDevice motor1 = updater.getMotor(4, 1);
		MotorDevice motor2 = updater.getMotor(4, 3);
		RelayDevice relay1 = updater.getRelay(1, 1);
		RelayDevice relay2 = updater.getRelay(3, 1);
		SolenoidDevice solenoid1 = updater.getSolenoid(4, 1);
		SolenoidDevice solenoid2 = updater.getSolenoid(2, 4);
		
		assertThat(motor1.getState(), is(new AnalogState(0.0)));
		assertThat(motor2.getState(), is(new AnalogState(0.0)));
		assertThat(relay1.getState(), is(RelayState.OFF));
		assertThat(relay2.getState(), is(RelayState.OFF));
		assertThat(solenoid1.getState(), is(DigitalState.OFF));
		assertThat(solenoid2.getState(), is(DigitalState.OFF));
	}
}
