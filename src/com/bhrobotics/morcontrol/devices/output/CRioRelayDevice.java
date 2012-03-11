package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

import edu.wpi.first.wpilibj.Relay;

public class CRioRelayDevice extends SingleChannelDevice implements RelayDevice {
	public static final RelayState DEFAULT_STATE = RelayState.OFF;
	private RelayState state = DEFAULT_STATE;
	private Relay relay;
	
	public CRioRelayDevice(int slot, int channel) {
		super(slot, channel);
		relay = new Relay(slot, channel);
		apply();
	}
	
	public boolean update(RelayState state) {
		if (state.equals(this.state)) {
			return false;
		} else {
			this.state = state;
			apply();
			return true;
		}
	}
	
	public void reset() {
		update(DEFAULT_STATE);
	}

	public void apply() {
		relay.set(state.toRelayValue());
	}
	
	public RelayState getState() {
		return state;
	}
}
