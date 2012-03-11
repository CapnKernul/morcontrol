package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

public class StubSolenoidDevice extends SingleChannelDevice implements SolenoidDevice {
	public static final boolean DEFAULT_STATE = false;
	private boolean state = DEFAULT_STATE;
	
	public StubSolenoidDevice(int slot, int channel) {
		super(slot, channel);
	}
	
	public boolean update(boolean state) {
		if (state == this.state) {
			return false;
		} else {
			this.state = state;
			return true;			
		}
	}

	public void reset() {
		update(DEFAULT_STATE);
	}
	
	public boolean getState() {
		return state;
	}

	public void apply() {
	}
}
