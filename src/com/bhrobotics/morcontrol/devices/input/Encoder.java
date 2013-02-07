package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;

public class Encoder implements Device {

	private edu.wpi.first.wpilibj.Encoder encoder;
	private Address address;

	public Encoder(Address encoderAddress, DigitalInput first, DigitalInput second) {
		encoder = new edu.wpi.first.wpilibj.Encoder(first.getRawDevice(), second.getRawDevice());
		address = encoderAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void reset() {
		encoder.reset();
	}

	public int getCount() {
		return encoder.get();
	}

	public double getRate() {
		return encoder.getRate();
	}

	public double getDistance() {
		return encoder.getDistance();
	}

	public DeviceType getDeviceType() {
		return DeviceType.ENCODER;
	}
}
