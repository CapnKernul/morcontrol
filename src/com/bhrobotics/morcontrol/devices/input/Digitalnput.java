package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public interface Digitalnput extends Device {

    public abstract Address getAddress();

    public abstract boolean getState();

}