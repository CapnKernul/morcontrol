package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;

public class DigitalInput implements Device {
    private Address address;
    private edu.wpi.first.wpilibj.DigitalInput input;

    public DigitalInput(Address address) {
	this.address = address;
	input = new edu.wpi.first.wpilibj.DigitalInput(address.getModule(), address.getChannel());
    }

    public Address getAddress() {
	return address;
    }

    public boolean getState() {
	return input.get();
    }

    public edu.wpi.first.wpilibj.DigitalInput getRawDevice() {
	return input;
    }

    public void reset() {
	// Has no reseted state
    }

    public DeviceType getDeviceType() {
	return DeviceType.DIGITAL_INPUT;
    }
}
