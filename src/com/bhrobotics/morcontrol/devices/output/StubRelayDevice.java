package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;
import com.bhrobotics.morcontrol.devices.output.RelayState;

public class StubRelayDevice extends SingleChannelDevice implements RelayDevice {
	public static final RelayState DEFAULT_STATE = RelayState.OFF;
	private RelayState state = DEFAULT_STATE;
	
	public StubRelayDevice(int slot, int channel) {
		super(slot, channel);
	}
	
	public boolean update(RelayState state) {
		if (state.equals(this.state)) {
			return false;
		} else {
			this.state = state;
			return true;
		}
	}
	
	public void reset() {
		update(DEFAULT_STATE);
	}

	public void apply() {
	}
	
	public RelayState getState() {
		return state;
	}
}
