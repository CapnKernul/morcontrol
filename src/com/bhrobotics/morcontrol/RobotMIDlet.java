package com.bhrobotics.morcontrol;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {

    private CompetitionRobot robot;
    
    public void robotInit() {
	robot = CompetitionRobot.getInstance();
    }

    public void disabled() {
	robot.switchMode(RobotMode.DISABLED);
    }

    public void autonomous() {
	robot.switchMode(RobotMode.AUTONOMOUS);
    }

    public void operatorControl() {
	robot.switchMode(RobotMode.OPERATOR_CONTROL);
    }
}