package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

import edu.wpi.first.wpilibj.AnalogChannel;

public class CRioAnalogInputDevice extends SingleChannelDevice implements AnalogInputDevice {
	public static final double DEFAULT_STATE = 0.0;
	private AnalogChannel input;
	private boolean hasChanged = false;
	private double state = DEFAULT_STATE;

	public CRioAnalogInputDevice(int module, int channel) {
		super(module, channel);
		input = new AnalogChannel(module, channel);
	}

	public double getState() {
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
		double newState = input.getVoltage();
		if (newState != state) {
			hasChanged = true;
		}
		
		state = newState;
	}
}
