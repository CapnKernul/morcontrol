package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

import edu.wpi.first.wpilibj.Solenoid;

public class CRioSolenoidDevice extends SingleChannelDevice implements SolenoidDevice {
	public static final boolean DEFAULT_STATE = false;
	private boolean state = DEFAULT_STATE;
	private Solenoid solenoid;
	
	public CRioSolenoidDevice(int slot, int channel) {
		super(slot, channel);
		solenoid = new Solenoid(slot, channel);
		apply();
	}
	
	public boolean update(boolean state) {
		if (state == this.state) {
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
		solenoid.set(state);
	}
	
	public boolean getState() {
		return state;
	}
}
