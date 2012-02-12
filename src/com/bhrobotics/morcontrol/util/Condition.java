package com.bhrobotics.morcontrol.util;

public class Condition {
	private Object obj = new Object();
	
	public void signal() {
		synchronized (obj) {
			obj.notify();
		}
	}
	
	public void await() {
		synchronized (obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				// Ignore.
			}
		}
	}
}
