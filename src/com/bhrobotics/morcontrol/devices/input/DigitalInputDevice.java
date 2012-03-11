package com.bhrobotics.morcontrol.devices.input;

public interface DigitalInputDevice extends InputDevice {
	public int getModule();
	public int getChannel();
	public boolean getState();
}
