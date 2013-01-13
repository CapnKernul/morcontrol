package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;

import edu.wpi.first.wpilibj.AnalogChannel;

public class AnalogInput {
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
}
