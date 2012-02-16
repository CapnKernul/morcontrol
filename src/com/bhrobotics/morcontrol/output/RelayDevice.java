package com.bhrobotics.morcontrol.output;

import com.bhrobotics.morcontrol.output.RelayState;
import com.bhrobotics.morcontrol.output.SingleChannelDevice;
import com.bhrobotics.morcontrol.util.OperatingSystem;

import edu.wpi.first.wpilibj.Relay;

public class RelayDevice extends SingleChannelDevice {
	public static final RelayState DEFAULT_STATE = RelayState.OFF;
	private RelayState state = DEFAULT_STATE;
	private Relay relay;
	
	public RelayDevice(int slot, int channel) {
		super(slot, channel);
		
		if (OperatingSystem.isCRio()) {
			relay = new Relay(slot, channel);
			relay.set(state.toRelayValue());
		}
	}
	
	public boolean update(RelayState state) {
		if (state.equals(this.state)) {
			return false;
		}
		
		this.state = state;
		if (OperatingSystem.isCRio()) {
			relay.set(state.toRelayValue());
		}
		
		return true;
	}
	
	public boolean reset() {
		return update(DEFAULT_STATE);
	}
	
	public RelayState getState() {
		return state;
	}
}
