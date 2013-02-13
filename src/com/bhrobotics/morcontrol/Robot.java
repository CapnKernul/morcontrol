package com.bhrobotics.morcontrol;

public interface Robot extends OIServerObserver {

	public abstract RobotMode getMode();

	public abstract void switchMode(RobotMode mode);

}