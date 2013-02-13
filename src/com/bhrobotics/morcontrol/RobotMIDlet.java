package com.bhrobotics.morcontrol;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
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
			competitionRobot = CompetitionRobot.getInstance();
			TProcessor deviceProcessor = new DeviceTransport.Processor(new DeviceService(competitionRobot, registry));
			TProcessor updateProcessor = new UpdateTransport.Processor(new UpdateService(registry.getMailbox()));
			System.out.println("Got here");
			oIServer = new AsyncOIServer(deviceProcessor, updateProcessor, 1515);
		} catch (InvalidAddressException e) {
			Logger.defaultLogger.fatal("could not initialize all devices");
			throw new RuntimeIOException(e);
		}

		oIServer.addObserver(competitionRobot);
	}

	public void disabled() {
		oIServer.stop();
		registry.stop();
		competitionRobot.switchMode(RobotMode.DISABLED);
	}

	public void autonomous() {
		oIServer.stop();
		competitionRobot.switchMode(RobotMode.AUTONOMOUS);
	}

	public void operatorControl() {
		oIServer.start();
		registry.start();
		competitionRobot.switchMode(RobotMode.OPERATOR_CONTROL);
	}
}
