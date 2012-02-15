package com.bhrobotics.morcontrol.util.concurrent;

import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;

public class SingleUseCondition {
	private Object obj = new Object();
	private boolean triggered = false;
	
	public void signal() {
		synchronized (obj) {
			triggered = true;
			obj.notifyAll();
		}
	}
	
	public void await() {
		synchronized (obj) {
			if (triggered) {
				return;
			}
			
			try {
				obj.wait();
			} catch (InterruptedException e) {
				throw new RuntimeInterruptedException(e);
			}
		}
	}
}
