package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;

public interface Solenoid {
    public static final boolean DEFAULT_STATE = false;

    public abstract void update(boolean state);

    public abstract void reset();

    public abstract Address getAddress();

    public abstract boolean getState();

}