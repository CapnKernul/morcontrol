package com.bhrobotics.morcontrol.support;

public abstract class AsynchronousUtils {
	public static void doAsync(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
