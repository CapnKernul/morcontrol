package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.oi.OIConnection;
import com.bhrobotics.morcontrol.oi.OIConnectionObserver;
import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.protobuf.InputUpdates;
import com.bhrobotics.morcontrol.protobuf.OutputUpdates;
import com.bhrobotics.morcontrol.util.io.Logger;
import com.bhrobotics.morcontrol.util.io.StdOutLogger;

public class Robot implements OIConnectionObserver {
	private ConnectionThread connectionThread = new ConnectionThread();
	private InputUpdatingThread inputUpdatingThread = new InputUpdatingThread();
	private OutputUpdatingThread outputUpdatingThread = new OutputUpdatingThread();
	private OIConnection connection;
	private InputUpdatesAdapter inputAdapter;
	private OutputUpdatesAdapter outputAdapter;
	private Logger logger;
	private RobotMode mode;
	
	public Robot() {
		this(new OIConnection(), new InputUpdatesAdapter(), new OutputUpdatesAdapter(), new StdOutLogger());
	}

	public Robot(OIConnection connection, InputUpdatesAdapter inputAdapter, OutputUpdatesAdapter outputAdapter, Logger logger) {
		this.connection = connection;
		this.inputAdapter = inputAdapter;
		this.outputAdapter = outputAdapter;
		this.logger = logger;
		
		connection.registerObserver(this);
		switchMode(RobotMode.DISABLED);
	}
	
	public void start() {
		connectionThread.start();
		inputUpdatingThread.start();
		outputUpdatingThread.start();
	}
	
	public RobotMode getMode() {
		return mode;
	}
	
	public void switchMode(RobotMode mode) {
		if (mode == null) {
			throw new NullPointerException("Mode cannot be null.");
		}
		
		if (!mode.equals(this.mode)) {
			stopCurrentMode();
			this.mode = mode;
			startCurrentMode();
		}
	}

	public void connectionOpened() {
		logger.info("Connection opened.");
		connection.write(inputAdapter.getAllInputs());
	}

	public void connectionClosed() {
		logger.info("Connection closed.");
		outputAdapter.reset();
	}
	
	private void startCurrentMode() {
		if (mode == null) {
			return;
		}
		
		if (mode.equals(RobotMode.DISABLED)) {
			startDisabled();
		} else if (mode.equals(RobotMode.AUTONOMOUS)) {
			startAutonomous();
		} else if (mode.equals(RobotMode.OPERATOR_CONTROL)) {
			startOperatorControl();
		}
	}
	
	private void stopCurrentMode() {
		if (mode == null) {
			return;
		}
		
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
		outputAdapter.apply();
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

	public void updateInputs() {
		try {
			InputUpdates updates = inputAdapter.getUpdatedInputs();
			connection.write(updates);
		} catch (OIException e) {
			logger.warn("Error while writing to stream: " + e.getMessage());
		}
	}
	
	public void updateOutputs() {
		try {
			OutputUpdates updates = connection.read();
			
			if (updates != null) {
				outputAdapter.update(updates);
			}
		} catch (OIException e) {
			logger.warn("Error while reading from stream: " + e.getMessage());
		}
	}
	
	private class ConnectionThread extends Thread {
		public void run() {
			logger.info("Started connection thread.");
			while (true) {
				connection.requireConnection();
			}
		}
	}

	private class InputUpdatingThread extends Thread {
		public void run() {
			logger.info("Started input updating thread.");
			while (true) {
				updateInputs();
			}
		}
	}
	
	private class OutputUpdatingThread extends Thread {
		public void run() {
			logger.info("Started output updating thread.");
			while (true) {
				updateOutputs();
			}
		}
	}
}