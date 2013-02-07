package com.bhrobotics.morcontrol;

import java.io.IOException;

import com.bhrobotics.morcontrol.devices.registry.AnalogInputRegistry;
import com.bhrobotics.morcontrol.devices.registry.DigitalInputRegistry;
import com.bhrobotics.morcontrol.devices.registry.EncoderRegistry;
import com.bhrobotics.morcontrol.devices.registry.ModuleMapping;
import com.bhrobotics.morcontrol.devices.registry.PWMRegistry;
import com.bhrobotics.morcontrol.devices.registry.RelayRegistry;
import com.bhrobotics.morcontrol.devices.registry.SolenoidRegistry;
import com.bhrobotics.morcontrol.io.RuntimeIOException;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {
    //private OIServer oIServer;
    //private CompetitionRobot competitionRobot;

    public void robotInit() {
	ModuleMapping mapping = ModuleMapping.getInstance();
	System.out.println(mapping.getAnalogModuleCount() + " " + mapping.getDigitalModuleCount() + " " + mapping.getSolenoidModuleCount());
	AnalogInputRegistry analogRegistry = new AnalogInputRegistry(mapping);	
	DigitalInputRegistry digitalRegistry = new DigitalInputRegistry(mapping);	
	EncoderRegistry encoderRegistry = new EncoderRegistry(digitalRegistry, mapping);
	PWMRegistry pwmsRegistry = new PWMRegistry(mapping);
	SolenoidRegistry solenoidRegistry = new SolenoidRegistry(mapping);
	RelayRegistry relayRegistry = new RelayRegistry (mapping);
	
//	try {
//	    oIServer = new SimpleOIServer(competitionRobot);
//	} catch (IOException e) {
//	    throw new RuntimeIOException(e);
//	}
//	
//	oIServer.addObserver(competitionRobot);
//	oIServer.start();
    }

    public void disabled() {
	//competitionRobot.switchMode(RobotMode.DISABLED);
    }

    public void autonomous() {
	//competitionRobot.switchMode(RobotMode.AUTONOMOUS);
    }

    public void operatorControl() {
	//competitionRobot.switchMode(RobotMode.OPERATOR_CONTROL);
    }
}
