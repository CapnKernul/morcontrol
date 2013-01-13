package com.bhrobotics.morcontrol.devices.output;

import java.util.Enumeration;
import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.DigitalModules;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.SolenoidModules;

public class OutputDevices {
	private static OutputDevices instance;
	
	private final DigitalModules digitalModules = DigitalModules.getInstance();
	private final SolenoidModules solenoidModules = SolenoidModules.getInstance();
	
	public static OutputDevices getInstance() {
		if (instance == null) {
			instance = new OutputDevices();
		}
		
		return instance;
	}
	
	private OutputDevices() {}
	
	public void updateMotor(Address address, double state) throws InvalidStateException {
		digitalModules.getMotor(address).update(state);
	}
	
	public void updateRelay(Address address, RelayState state) {
		digitalModules.getRelay(address).update(state);
	}
	
	public void updateSolenoid(Address address, boolean state) {
		solenoidModules.getSolenoid(address).update(state);
	}
	
	public void reset() {
		resetMotors();
		resetRelays();
		resetSolenoids();
	}

	private void resetMotors() {
		Enumeration e = digitalModules.motors();
		while (e.hasMoreElements()) {
			Motor motor = (Motor) e.nextElement();
			motor.reset();
		}
	}

	private void resetRelays() {
		Enumeration e = digitalModules.relays();
		while (e.hasMoreElements()) {
			Relay relay = (Relay) e.nextElement();
			relay.reset();
		}
	}

	private void resetSolenoids() {
		Enumeration e = solenoidModules.solenoids();
		while (e.hasMoreElements()) {
			Solenoid solenoid = (Solenoid) e.nextElement();
			solenoid.reset();
		}
	}
}
