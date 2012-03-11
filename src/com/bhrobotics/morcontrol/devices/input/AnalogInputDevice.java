package com.bhrobotics.morcontrol.devices.input;

public interface AnalogInputDevice extends InputDevice {
	public int getModule();
	public int getChannel();
	public double getState();
}
