package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidStateException;

public interface PWM {

    public abstract void update(int state) throws InvalidStateException;

    public abstract void reset();

    public abstract Address getAddress();

    public abstract double getState();

}