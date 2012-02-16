package com.bhrobotics.morcontrol;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMIDlet extends IterativeRobot {
	private Robot robot;
	
    public void robotInit() {
    	robot = new Robot();
    }
    
    public void disabledInit() {
    	robot.switchMode(Robot.Mode.DISABLED);
    }
    
    public void autonomousInit() {
    	robot.switchMode(Robot.Mode.AUTONOMOUS);
    }
    
    public void teleopInit() {
    	robot.switchMode(Robot.Mode.OPERATOR_CONTROL);
    }
}
