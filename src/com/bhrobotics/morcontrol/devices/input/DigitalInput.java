package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;

public class DigitalInput {
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
}
