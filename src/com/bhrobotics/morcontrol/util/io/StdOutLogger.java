package com.bhrobotics.morcontrol.util.io;

public class StdOutLogger implements Logger {
	public void trace(String message) {
		System.out.println("[TRACE] " + message);
	}

	public void debug(String message) {
		System.out.println("[DEBUG] " + message);
	}

	public void info(String message) {
		System.out.println("[INFO] " + message);
	}

	public void warn(String message) {
		System.out.println("[WARN] " + message);
	}

	public void error(String message) {
		System.out.println("[ERROR] " + message);
	}

	public void fatal(String message) {
		System.out.println("[FATAL] " + message);
	}
}
