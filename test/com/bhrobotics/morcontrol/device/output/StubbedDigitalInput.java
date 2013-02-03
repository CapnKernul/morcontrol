package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;

public class StubbedDigitalInput implements DigitalInput{
	private Address address;
	private boolean state = false;
	
	public StubbedDigitalInput (Address address) {
		this.address = address;
	}

	@Override
	public void reset() {
		state = false;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean bool) {
		state = bool;
	}
}
