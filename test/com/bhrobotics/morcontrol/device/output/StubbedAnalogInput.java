package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;

public class StubbedAnalogInput implements AnalogInput{
	private Address address;
	private int state = 0;
	
	public StubbedAnalogInput (Address address){
		this.address = address;
	}

	@Override
	public void reset() {
		state = 0;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public double getState() {
		return state;
	}
}
