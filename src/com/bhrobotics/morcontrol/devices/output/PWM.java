package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidStateException;

public interface PWM extends Device {

    public static final int DEFAULT_STATE = 127;
    public static final int MAX_VALUE = 255;
    public static final int MIN_VALUE = 1;

    public abstract void update(int state) throws InvalidStateException;

    public abstract void reset();

    public abstract Address getAddress();

    public abstract int getState();

}