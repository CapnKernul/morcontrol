package com.bhrobotics.morcontrol.util;

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
			count--;
			
			if (count < 0) {
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// Ignore.
				}
			}
		}
	}
}
