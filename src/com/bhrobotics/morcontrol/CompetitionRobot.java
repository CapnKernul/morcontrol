package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class CompetitionRobot implements Robot {

    private final Logger logger = Logger.defaultLogger;
    private RobotMode mode = RobotMode.DISABLED;
    private DeviceRegistry registry;
    private AsyncOIServer server;

    private static CompetitionRobot instance;

    public static CompetitionRobot getInstance() {
	if (instance == null) {
	    instance = new CompetitionRobot();
	}

	return instance;
    }

    private CompetitionRobot() {
	try {
	    registry = new DeviceRegistry();
	} catch (InvalidAddressException e) {
	    Logger.defaultLogger.fatal("Could not initialize at " + e.getAddress().toString());
	}
	server = new AsyncOIServer(this, 1515);
	startDisabled();
    }

    public RobotMode getMode() {
	return mode;
    }

    public DeviceRegistry getRegistry() {
	return registry;
    }

    public void switchMode(RobotMode mode) {
	if (!mode.equals(this.mode)) {
	    stopCurrentMode();
	    this.mode = mode;
	    startCurrentMode();
	}
    }

    private void startCurrentMode() {
	if (mode.equals(RobotMode.DISABLED)) {
	    startDisabled();
	} else if (mode.equals(RobotMode.AUTONOMOUS)) {
	    startAutonomous();
	} else if (mode.equals(RobotMode.OPERATOR_CONTROL)) {
	    startOperatorControl();
	}
    }

    private void stopCurrentMode() {
	if (mode.equals(RobotMode.DISABLED)) {
	    stopDisabled();
	} else if (mode.equals(RobotMode.AUTONOMOUS)) {
	    stopAutonomous();
	} else if (mode.equals(RobotMode.OPERATOR_CONTROL)) {
	    stopOperatorControl();
	}
    }

    private void startDisabled() {
	server.closeListeners();
	server.openListeners();
	logger.info("Entered disabled mode.");
    }

    private void stopDisabled() {
	logger.info("Exited disabled mode.");
    }

    private void startOperatorControl() {
	server.startProcess();
	registry.start();
	logger.info("Entered operator control mode.");
    }

    private void stopOperatorControl() {
	registry.stop();
	server.endProcess();
	logger.info("Exited operator control mode.");
    }

    private void startAutonomous() {
	logger.info("Entered autonomous mode.");
    }

    private void stopAutonomous() {
	logger.info("Exited autonomous mode.");
    }
}