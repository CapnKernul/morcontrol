package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidStateException;

import edu.wpi.first.wpilibj.Victor;

public class Motor {
	public static final double DEFAULT_STATE = 0.0;
	public static final double MAX_VALUE = 1.0;
	public static final double MIN_VALUE = -1.0;
	
	private Address address;
	private Victor victor;
	
	public Motor(Address address) {
		this.address = address;
		victor = new Victor(address.getModule(), address.getChannel());
	}
	
	public void update(double state) throws InvalidStateException {
		if (state > MAX_VALUE || state < MIN_VALUE) {
			throw new InvalidStateException("Motor state out of range.");
		} else {
			victor.set(state);	
		}
	}
	
	public void reset() {
		victor.set(DEFAULT_STATE);
	}
	
	public Address getAddress() {
		return address;
	}
	
	public double getState() {
		return victor.get();
	}
}
