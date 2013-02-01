package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.DeviceRegistry;

public interface Robot extends OIServerObserver {

    public abstract RobotMode getMode();

    public abstract void switchMode(RobotMode mode);

    public abstract void oiConnected();

    public abstract void oiDisconnected();

    public abstract DeviceRegistry getDeviceRegistry();
    
}