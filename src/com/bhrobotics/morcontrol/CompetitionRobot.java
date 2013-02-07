/*package com.bhrobotics.morcontrol;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class CompetitionRobot implements Robot {
    private final Logger logger = Logger.defaultLogger;
    private RobotMode mode = RobotMode.DISABLED;

    private static CompetitionRobot instance;

    public static CompetitionRobot getInstance() {
	if (instance == null) {
	    instance = new CompetitionRobot();
	}

	return instance;
    }

    private CompetitionRobot() {
	startDisabled();
    }

    public RobotMode getMode() {
	return mode;
    }

    public void switchMode(RobotMode mode) {
	if (!mode.equals(this.mode)) {
	    stopCurrentMode();
	    this.mode = mode;
	    startCurrentMode();
	}
    }

    public void oiConnected() {
	logger.info("OI connected.");
    }

    public void oiDisconnected() {
	logger.info("OI disconnected.");
	outputDevices.reset();
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
	logger.info("Entered disabled mode.");
    }

    private void stopDisabled() {
	logger.info("Exited disabled mode.");
    }

    private void startOperatorControl() {
	logger.info("Entered operator control mode.");
    }

    private void stopOperatorControl() {
	logger.info("Exited operator control mode.");
    }

    private void startAutonomous() {
	logger.info("Entered autonomous mode.");
    }

    private void stopAutonomous() {
	logger.info("Exited autonomous mode.");
    }
}*/