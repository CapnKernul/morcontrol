package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.SingleChannelDevice;

import edu.wpi.first.wpilibj.Victor;

public class CRioMotorDevice extends SingleChannelDevice implements MotorDevice {
	public static final double DEFAULT_STATE = 0.0;
	public static final double MAX_VALUE = 1.0;
	public static final double MIN_VALUE = -1.0;
	private double state = DEFAULT_STATE;
	private Victor victor;
	
	public CRioMotorDevice(int slot, int channel) {
		super(slot, channel);
		victor = new Victor(slot, channel);
		apply();
	}
	
	public boolean update(double state) {
		if (state == this.state) {
			return false;
		} else if (state > MAX_VALUE || state < MIN_VALUE) {
			throw new InvalidStateException("Motor state out of range.");
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
		victor.set(state);
	}
	
	public double getState() {
		return state;
	}
}
