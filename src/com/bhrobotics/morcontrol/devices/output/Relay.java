package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;

public class Relay {
	public static final RelayState DEFAULT_STATE = RelayState.OFF;
	
	private Address address;
	private edu.wpi.first.wpilibj.Relay relay;
	private RelayState state = DEFAULT_STATE;
	
	public Relay(Address address) {
		this.address = address;
		relay = new edu.wpi.first.wpilibj.Relay(address.getModule(), address.getChannel());
	}
	
	public void update(RelayState state) {
		this.state = state;
		relay.set(state.toRelayValue());
	}
	
	public void reset() {
		update(DEFAULT_STATE);
	}
	
	public Address getAddress() {
		return address;
	}
	
	public RelayState getState() {
		return state;
	}
}
