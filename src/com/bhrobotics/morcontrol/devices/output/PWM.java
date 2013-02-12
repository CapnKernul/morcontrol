package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class PWM implements Device {
	private static final int MAX_VALUE = 255;
	private static final int MIN_VALUE = 1;
	private static final int DEFAULT_STATE = 127;

	private Address address;
	private edu.wpi.first.wpilibj.PWM pwm;

	public PWM(Address address) {
		this.address = address;
		pwm = new edu.wpi.first.wpilibj.PWM(address.getModule(), address.getChannel());
		Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
	}

	public void update(int state) throws InvalidStateException {
		if (state > MAX_VALUE || state < MIN_VALUE) {
			throw new InvalidStateException("Motor state out of range.");
		} else {
			pwm.setRaw(state);
		}
	}

	public void reset() {
		pwm.setRaw(DEFAULT_STATE);
	}

	public Address getAddress() {
		return address;
	}

	public int getState() {
		return pwm.getRaw();
	}

	public DeviceType getDeviceType() {
		return DeviceType.PWM;
	}
}