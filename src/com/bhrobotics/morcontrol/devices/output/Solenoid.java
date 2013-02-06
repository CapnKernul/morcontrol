package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public interface Solenoid extends Device {
    public static final boolean DEFAULT_STATE = false;

    public abstract void update(boolean state);

    public abstract void reset();

    public abstract Address getAddress();

    public abstract boolean getState();

}