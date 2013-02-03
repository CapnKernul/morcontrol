package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;

public class StubbedPWM implements PWM {
	private Address address;
	private int state = DEFAULT_STATE;
	
	
	public StubbedPWM(Address address) {
		this.address = address;
	}
	
    @Override
    public void update(int state) throws InvalidStateException {
    	if (state > MAX_VALUE || state < MIN_VALUE) {
    		throw new InvalidStateException("Motor state out of range.");
    	} else {
    		this.state = state;
    		System.out.println("PWM at " + getAddress().toString() + " has been updated to " + state + ".");
    	}	
    }

    @Override
    public void reset() {
		this.state = DEFAULT_STATE;
    }

    @Override
    public Address getAddress() {
    	return address;
    }

    @Override
    public int getState() {
    	return state;
    }

    public void setState(int num) {
    	state = num;
    }
}
