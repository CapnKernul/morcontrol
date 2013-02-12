package com.bhrobotics.morcontrol;

import java.io.IOException;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.io.DeviceService;
import com.bhrobotics.morcontrol.io.DeviceTransport;
import com.bhrobotics.morcontrol.io.RuntimeIOException;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {
     private OIServer oIServer;
     private CompetitionRobot competitionRobot;

    public void robotInit() {
	try {
	    TProcessor deviceProcessor = new DeviceTransport.Processor(new DeviceService(competitionRobot, null)); 
	    oIServer = new AsyncOIServer(competitionRobot);
	} catch (IOException e) {
	    throw new RuntimeIOException(e);
	}

	oIServer.addObserver(competitionRobot);
	oIServer.start();
    }

    public void disabled() {
	// competitionRobot.switchMode(RobotMode.DISABLED);
    }

    public void autonomous() {
	// competitionRobot.switchMode(RobotMode.AUTONOMOUS);
    }

    public void operatorControl() {
	// competitionRobot.switchMode(RobotMode.OPERATOR_CONTROL);
    }
}
