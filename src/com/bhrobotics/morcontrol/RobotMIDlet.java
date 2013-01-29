package com.bhrobotics.morcontrol;

import java.io.IOException;

import com.bhrobotics.morcontrol.io.RuntimeIOException;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {
    private OIServer oIServer;
    private CompetitionRobot competitionRobot;

    public void robotInit() {
	competitionRobot = CompetitionRobot.getInstance();
	try {
	    oIServer = new SimpleOIServer(competitionRobot);
	} catch (IOException e) {
	    throw new RuntimeIOException(e);
	}
	
	oIServer.addObserver(competitionRobot);
	oIServer.start();
    }

    public void disabled() {
	competitionRobot.switchMode(RobotMode.DISABLED);
    }

    public void autonomous() {
	competitionRobot.switchMode(RobotMode.AUTONOMOUS);
    }

    public void operatorControl() {
	competitionRobot.switchMode(RobotMode.OPERATOR_CONTROL);
    }
}
