package com.bhrobotics.morcontrol.devices.output;

public interface MotorDevice extends OutputDevice {
	public int getModule();
	public int getChannel();
	public boolean update(double state);
	public double getState();
}
