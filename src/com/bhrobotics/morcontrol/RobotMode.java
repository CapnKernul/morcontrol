package com.bhrobotics.morcontrol;

public class RobotMode {
	public static final RobotMode DISABLED = new RobotMode();
	public static final RobotMode AUTONOMOUS = new RobotMode();
	public static final RobotMode OPERATOR_CONTROL = new RobotMode();
	
	private RobotMode() {
	}
}
