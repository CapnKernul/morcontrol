package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;

public interface Robot {

    public abstract RobotMode getMode();

    public abstract void switchMode(RobotMode mode);
    
    public abstract DeviceRegistry getRegistry();

}