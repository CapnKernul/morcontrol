package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class Solenoid implements Device {

	private static final boolean DEFAULT_STATE = false;
	private Address address;
	private edu.wpi.first.wpilibj.Solenoid solenoid;

	public Solenoid(Address address) {
		this.address = address;
		solenoid = new edu.wpi.first.wpilibj.Solenoid(address.getModule(), address.getChannel());
		Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
	}

	public void update(boolean state) {
		solenoid.set(state);
	}

	public void reset() {
		update(DEFAULT_STATE);
	}

	public Address getAddress() {
		return address;
	}

	public boolean getState() {
		return solenoid.get();
	}

	public DeviceType getDeviceType() {
		return DeviceType.SOLENOID;
	}
}
