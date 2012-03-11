package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

public class StubDigitalInputDevice extends SingleChannelDevice implements DigitalInputDevice {
	public static final boolean DEFAULT_STATE = false;
	private boolean hasChanged = false;
	private boolean state = DEFAULT_STATE;
	
	public StubDigitalInputDevice(int module, int channel) {
		super(module, channel);
	}

	public boolean getState() {
		return state;
	}
	
	public void setState(boolean newState) {
		if (newState != state) {
			hasChanged = true;
		}
		
		state = newState;
	}

	public boolean hasChanged() {
		boolean result = hasChanged;
		hasChanged = false;
		return result;
	}
}
