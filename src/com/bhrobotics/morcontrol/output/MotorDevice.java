package com.bhrobotics.morcontrol.output;

import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.output.SingleChannelDevice;
import com.bhrobotics.morcontrol.util.OperatingSystem;

import edu.wpi.first.wpilibj.Victor;

public class MotorDevice extends SingleChannelDevice {
	public static final AnalogState DEFAULT_STATE = new AnalogState(0.0);
	private AnalogState state = DEFAULT_STATE;
	private Victor victor;
	
	public MotorDevice(int slot, int channel) {
		super(slot, channel);
		
		if (OperatingSystem.isCRio()) {
			victor = new Victor(slot, channel);
			victor.set(state.toDouble());
		}
	}
	
	public boolean update(AnalogState state) {
		if (state.equals(this.state)) {
			return false;
		}
		
		this.state = state;	
		if (OperatingSystem.isCRio()) {
			victor.set(state.toDouble());
		}
		
		return true;
	}
	
	public boolean reset() {
		return update(DEFAULT_STATE);
	}
	
	public void reapply() {
		if (OperatingSystem.isCRio()) {
			victor.set(state.toDouble());
		}
	}
	
	public AnalogState getState() {
		return state;
	}
}
