package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.RelayState;

public class StubbedRelay implements Relay {
	private Address address;
	private RelayState state = DEFAULT_STATE;
	
	public StubbedRelay(Address address) {
		this.address = address;
	}

	@Override
	public void update(RelayState state) {
		this.state = state;
		System.out.println("Relay state has been changed to " + state);
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
	public RelayState getState() {
		return state;
	}
	
	public void setState(RelayState relay) {
		state = relay;
	}
}
