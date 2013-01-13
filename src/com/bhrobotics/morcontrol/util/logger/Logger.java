package com.bhrobotics.morcontrol.util.logger;

public interface Logger {
	public static final Logger defaultLogger = new StdOutLogger();
	public void trace(String message);
	public void debug(String message);
	public void info(String message);
	public void warn(String message);
	public void error(String message);
	public void fatal(String message);
}
