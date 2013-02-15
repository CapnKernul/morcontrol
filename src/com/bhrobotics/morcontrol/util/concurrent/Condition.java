package com.bhrobotics.morcontrol.util.concurrent;

public class Condition {
	private Object obj = new Object();

	public void signal() {
		synchronized (obj) {
			obj.notifyAll();
		}
	}

	public void await() throws InterruptedException {
		synchronized (obj) {
			obj.wait();
		}
	}
}
