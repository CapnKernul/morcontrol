package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class Motor implements Device {
	private static final int MAX_VALUE = 255;
	private static final int MIN_VALUE = 1;
	private static final int DEFAULT_STATE = 127;

	private Address address;
	private edu.wpi.first.wpilibj.Victor motor;

	public Motor(Address address) {
		this.address = address;
		motor = new edu.wpi.first.wpilibj.Victor(address.getModule(), address.getChannel());
		Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
	}

	public void update(int state) throws InvalidStateException {
		if (state > MAX_VALUE || state < MIN_VALUE) {
			throw new InvalidStateException("Motor state out of range.");
		} else {
			motor.setRaw(state);
		}
	}

	public void reset() {
		motor.setRaw(DEFAULT_STATE);
	}

	public Address getAddress() {
		return address;
	}

	public int getState() {
		return motor.getRaw();
	}

	public DeviceType getDeviceType() {
		return DeviceType.PWM;
	}
}