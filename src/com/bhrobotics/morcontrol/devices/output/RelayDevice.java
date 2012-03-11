package com.bhrobotics.morcontrol.devices.output;

public interface RelayDevice extends OutputDevice {
	public int getModule();
	public int getChannel();
	public boolean update(RelayState state);
	public RelayState getState();
}
