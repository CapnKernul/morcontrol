package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Device;

public interface DigitalInput extends Device {
    
    public abstract boolean getState();

}