package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Device;

public interface AnalogInput extends Device {

    public abstract double getState();
    
}