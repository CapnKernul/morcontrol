package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidStateException;

public class RobotPWM implements PWM {
	private Address address;
	private edu.wpi.first.wpilibj.PWM pwm;
	
	public RobotPWM(Address address) {
		this.address = address;
		pwm = new edu.wpi.first.wpilibj.PWM(address.getModule(), address.getChannel());
	}
	
	public void update(int state) throws InvalidStateException {
		if (state > MAX_VALUE || state < MIN_VALUE) {
			throw new InvalidStateException("Motor state out of range.");
		} else {
			pwm.setRaw(state);	
		}
	}
	
	public void reset() {
		pwm.setRaw(DEFAULT_STATE);
	}
	
	public Address getAddress() {
		return address;
	}
	
	public int getState() {
		return pwm.getRaw();
	}
}