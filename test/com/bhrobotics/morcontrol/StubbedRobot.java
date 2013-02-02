package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.DeviceRegistry;

public class StubbedRobot implements Robot {
    
    private RobotMode mode = RobotMode.DISABLED;
	private DeviceRegistry registry = new StubbedDeviceRegistry();
    
    public RobotMode getMode() {
	return mode;
    }

    public void switchMode(RobotMode mode) {
	this.mode = mode;
    }

    public void oiConnected() {
	System.out.println("Server connected!");
    }

    public void oiDisconnected() {
	System.out.println("Server disconnect!");
    }

    public DeviceRegistry getDeviceRegistry() {
	return registry;
    }

}
