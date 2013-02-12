package com.bhrobotics.morcontrol;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;
import com.bhrobotics.morcontrol.io.DeviceService;
import com.bhrobotics.morcontrol.io.DeviceTransport;
import com.bhrobotics.morcontrol.io.RuntimeIOException;
import com.bhrobotics.morcontrol.io.UpdateService;
import com.bhrobotics.morcontrol.io.UpdateTransport;
import com.bhrobotics.morcontrol.util.logger.Logger;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {

	private OIServer oIServer;
	private CompetitionRobot competitionRobot;
	private DeviceRegistry registry;

	public void robotInit() {
		try {
			registry = new DeviceRegistry();
			TProcessor deviceProcessor = new DeviceTransport.Processor(new DeviceService(competitionRobot, registry));
			TProcessor updateProcessor = new UpdateTransport.Processor(new UpdateService(registry.getMailbox()));
			oIServer = new AsyncOIServer(deviceProcessor, updateProcessor, 1515);
		} catch (InvalidAddressException e) {
			Logger.defaultLogger.fatal("could not initialize all devices");
			throw new RuntimeIOException(e);
		}

		oIServer.addObserver(competitionRobot);
		oIServer.addObserver(registry);
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
