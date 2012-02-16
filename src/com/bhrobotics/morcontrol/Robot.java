package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.output.DeviceUpdater;

public class Robot {
	public static class Mode {
		public static final Mode DISABLED = new Mode();
		public static final Mode AUTONOMOUS = new Mode();
		public static final Mode OPERATOR_CONTROL = new Mode();
		
		private Mode() {
		}
	}
	
	private DeviceUpdater updater;
	private Mode mode;
	
	public Robot() {
		this(new DeviceUpdater());
	}

	public Robot(DeviceUpdater updater) {
		this.updater = updater;
		switchMode(Mode.DISABLED);
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public void switchMode(Mode mode) {
		if (mode == null) {
			throw new NullPointerException("Mode cannot be null.");
		}
		
		if (!mode.equals(this.mode)) {
			stopCurrentMode();
			this.mode = mode;
			startCurrentMode();
		}
	}
	
	private void startCurrentMode() {
		if (mode == null) {
			return;
		}
		
		if (mode.equals(Mode.DISABLED)) {
			startDisabled();
		} else if (mode.equals(Mode.AUTONOMOUS)) {
			startAutonomous();
		} else if (mode.equals(Mode.OPERATOR_CONTROL)) {
			startOperatorControl();
		}
	}
	
	private void stopCurrentMode() {
		if (mode == null) {
			return;
		}
		
		if (mode.equals(Mode.DISABLED)) {
			stopDisabled();
		} else if (mode.equals(Mode.AUTONOMOUS)) {
			stopAutonomous();
		} else if (mode.equals(Mode.OPERATOR_CONTROL)) {
			stopOperatorControl();
		}
	}
	
	public void startDisabled() {
	}
	
	public void stopDisabled() {
	}

	public void startOperatorControl() {
		updater.reset();
	}
	
	public void stopOperatorControl() {
	}
	
	public void startAutonomous() {
	}
	
	public void stopAutonomous() {
	}
}