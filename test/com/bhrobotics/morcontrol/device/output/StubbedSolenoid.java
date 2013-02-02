package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

public class StubbedSolenoid implements Solenoid {
	private Address address;
	private boolean state = DEFAULT_STATE;
	
	public StubbedSolenoid (Address address) {
		this.address = address;
	}

	@Override
	public void update(boolean state) {
		this.state = state;
		System.out.println("Solenoid state has been changed to " + state);
	}

	@Override
	public void reset() {
		this.state = DEFAULT_STATE;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public boolean getState() {
		return state;
	}
}
