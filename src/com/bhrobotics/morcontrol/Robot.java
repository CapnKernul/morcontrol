package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.input.InputDevices;
import com.bhrobotics.morcontrol.devices.output.OutputDevices;

public interface Robot extends OIServerObserver {

    public abstract RobotMode getMode();

    public abstract void switchMode(RobotMode mode);

    public abstract void oiConnected();

    public abstract void oiDisconnected();
    
    public abstract InputDevices getInputDevices();
    
    public abstract OutputDevices getOutputDevices();
}