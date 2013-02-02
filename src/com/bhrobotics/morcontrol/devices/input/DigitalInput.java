package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public interface DigitalInput extends Device {

    public abstract Address getAddress();

    public abstract boolean getState();

}