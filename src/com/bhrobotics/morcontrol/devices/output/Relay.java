package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class Relay implements Device {

	private static final RelayState DEFAULT_STATE = RelayState.OFF;
	private Address address;
	private edu.wpi.first.wpilibj.Relay relay;
	private RelayState state = DEFAULT_STATE;

	public Relay(Address address) {
		this.address = address;
		relay = new edu.wpi.first.wpilibj.Relay(address.getModule(), address.getChannel());
		Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
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

	public DeviceType getDeviceType() {
		return DeviceType.RELAY;
	}
}
