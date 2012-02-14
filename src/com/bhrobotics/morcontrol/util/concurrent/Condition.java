package com.bhrobotics.morcontrol.util.concurrent;

import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;

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
				throw new RuntimeInterruptedException(e);
			}
		}
	}
}
