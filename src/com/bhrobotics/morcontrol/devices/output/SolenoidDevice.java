package com.bhrobotics.morcontrol.devices.output;

public interface SolenoidDevice extends OutputDevice {
	public int getModule();
	public int getChannel();
	public boolean update(boolean state);
	public boolean getState();
}
