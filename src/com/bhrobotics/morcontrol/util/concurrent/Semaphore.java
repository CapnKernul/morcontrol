package com.bhrobotics.morcontrol.util.concurrent;

import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;

public class Semaphore {
	private Object obj = new Object();
	private int count = 0;
	
	public void release() {
		synchronized (obj) {
			count++;
			obj.notify();
		}
	}
	
	public void acquire() {
		synchronized (obj) {
			try {
				count--;
				
				if (count < 0) {
					obj.wait();
				}
			} catch (InterruptedException e) {
				count++;
				throw new RuntimeInterruptedException(e);
			}
		}
	}
}
