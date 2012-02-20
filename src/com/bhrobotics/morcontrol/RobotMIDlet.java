package com.bhrobotics.morcontrol;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMIDlet extends IterativeRobot {
	private Robot robot;
	
    public void robotInit() {
    	robot = new Robot();
    	robot.start();
    }
    
    public void disabledInit() {
    	robot.switchMode(RobotMode.DISABLED);
    }
    
    public void autonomousInit() {
    	robot.switchMode(RobotMode.AUTONOMOUS);
    }
    
    public void teleopInit() {
    	robot.switchMode(RobotMode.OPERATOR_CONTROL);
    }
}
