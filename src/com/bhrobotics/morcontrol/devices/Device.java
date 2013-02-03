package com.bhrobotics.morcontrol.devices;


public interface Device {
    
    public abstract Address getAddress();
    
    public abstract void reset();
    
    public abstract DeviceType getDeviceType();
    
    
}
