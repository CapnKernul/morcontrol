package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Device;

public interface Encoder extends Device {
    
    public abstract int getCount();
    
    public abstract double getRate();
    
    public abstract double getDistance();
    
}