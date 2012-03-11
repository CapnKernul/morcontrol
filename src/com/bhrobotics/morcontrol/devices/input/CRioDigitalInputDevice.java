package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

import edu.wpi.first.wpilibj.DigitalInput;

public class CRioDigitalInputDevice extends SingleChannelDevice implements DigitalInputDevice {
	public static final boolean DEFAULT_STATE = false;
	private DigitalInput input;
	private boolean hasChanged = false;
	private boolean state = DEFAULT_STATE;
	
	public CRioDigitalInputDevice(int module, int channel) {
		super(module, channel);
		input = new DigitalInput(module, channel);
	}

	public boolean getState() {
		update();
		return state;
	}

	public boolean hasChanged() {
		update();
		boolean result = hasChanged;
		hasChanged = false;
		return result;
	}
	
	private void update() {
		boolean newState = input.get();
		if (newState != state) {
			hasChanged = true;
		}
		
		state = newState;
	}
}
