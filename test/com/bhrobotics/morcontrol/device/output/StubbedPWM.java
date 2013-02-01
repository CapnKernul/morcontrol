package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;

public class StubbedPWM implements PWM {

    @Override
    public void update(int state) throws InvalidStateException {
	// TODO Auto-generated method stub

    }

    @Override
    public void reset() {
	// TODO Auto-generated method stub

    }

    @Override
    public Address getAddress() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public double getState() {
	// TODO Auto-generated method stub
	return 0;
    }

}
