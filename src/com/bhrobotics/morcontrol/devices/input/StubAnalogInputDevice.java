package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

public class StubAnalogInputDevice extends SingleChannelDevice implements AnalogInputDevice {
	public static final double DEFAULT_STATE = 0.0;
	private boolean hasChanged = false;
	private double state = DEFAULT_STATE;

	public StubAnalogInputDevice(int module, int channel) {
		super(module, channel);
	}

	public double getState() {
		return state;
	}
	
	public void setState(double newState) {
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
