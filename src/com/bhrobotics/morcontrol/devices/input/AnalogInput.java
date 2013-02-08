package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;

import edu.wpi.first.wpilibj.AnalogChannel;

public class AnalogInput implements Device {
    private Address address;
    private AnalogChannel input;

    public AnalogInput(Address address) {
	this.address = address;
	input = new AnalogChannel(address.getModule(), address.getChannel());
    }

    public Address getAddress() {
	return address;
    }

    public double getState() {
	return input.getVoltage();
    }

    public void reset() {
	// Has no reset state
    }

    public DeviceType getDeviceType() {
	return DeviceType.ANALOG_INPUT;
    }
}
