package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.oi.OIConnection;
import com.bhrobotics.morcontrol.oi.OIConnectionObserver;
import com.bhrobotics.morcontrol.oi.messages.Message;

public class Robot implements OIConnectionObserver {
	private OutputUpdatingThread outputUpdatingThread = new OutputUpdatingThread();
	private OIConnection connection;
	private OutputMessageAdapter adapter;
	private RobotMode mode;
	
	public Robot() {
		this(new OIConnection(), new OutputMessageAdapter());
	}

	public Robot(OIConnection connection, OutputMessageAdapter adapter) {
		this.connection = connection;
		this.adapter = adapter;
		
		connection.registerObserver(this);
		switchMode(RobotMode.DISABLED);
	}
	
	public void start() {
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
	}

	public void connectionClosed() {
		adapter.reset();
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
		System.out.println("[MorControl] Entered disabled mode.");
	}
	
	private void stopDisabled() {
		System.out.println("[MorControl] Exited disabled mode.");
	}

	private void startOperatorControl() {
		System.out.println("[MorControl] Entered operator control mode.");
		adapter.reapply();
	}
	
	private void stopOperatorControl() {
		System.out.println("[MorControl] Exited operator control mode.");
	}
	
	private void startAutonomous() {
		System.out.println("[MorControl] Entered autonomous mode.");
	}
	
	private void stopAutonomous() {
		System.out.println("[MorControl] Exited autonomous mode.");
	}
	
	
	
	public void updateOutputs() {
		Message[] messages = connection.read();
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			adapter.update(message);
		}
	}
	
	private class OutputUpdatingThread extends Thread {
		public void run() {
			System.out.println("[MorControl] Started output updating thread.");
			while (true) {
				updateOutputs();
			}
		}
	}
}