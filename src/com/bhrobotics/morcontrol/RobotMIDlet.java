package com.bhrobotics.morcontrol;

import java.io.IOException;

import com.bhrobotics.morcontrol.io.RuntimeIOException;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMIDlet extends IterativeRobot {
	private OIServer server;
	private Robot robot;
	
    public void robotInit() {
    	try {
			server = new OIServer();
		} catch (IOException e) {
			throw new RuntimeIOException(e);
		}
    	
    	robot = Robot.getInstance();
    	server.addObserver(robot);
    	server.start();
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
    
    public void disabledContinuous() {}
    public void autonomousContinuous() {}
    public void teleopContinuous() {}
    
    public void disabledPeriodic() {}
    public void autonomousPeriodic() {}
    public void teleopPeriodic() {}
}
