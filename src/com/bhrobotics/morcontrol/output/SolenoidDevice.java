package com.bhrobotics.morcontrol.output;

import com.bhrobotics.morcontrol.output.DigitalState;
import com.bhrobotics.morcontrol.output.SingleChannelDevice;
import com.bhrobotics.morcontrol.util.OperatingSystem;

import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidDevice extends SingleChannelDevice {
	public static final DigitalState DEFAULT_STATE = DigitalState.OFF;
	private DigitalState state = DEFAULT_STATE;
	private Solenoid solenoid;
	
	public SolenoidDevice(int slot, int channel) {
		super(slot, channel);
		
		if (OperatingSystem.isCRio()) {
			solenoid = new Solenoid(slot, channel);
			solenoid.set(state.toBoolean());
		}
	}
	
	public boolean update(DigitalState state) {
		if (state.equals(this.state)) {
			return false;
		}
		
		this.state = state;
		if (OperatingSystem.isCRio()) {
			solenoid.set(state.toBoolean());
		}
		
		return true;
	}

	public boolean reset() {
		return update(DEFAULT_STATE);
	}
	
	public DigitalState getState() {
		return state;
	}
}
