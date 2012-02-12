package com.bhrobotics.morcontrol.util;

public class ReentrantMutex {
	private int waitingThreads = 0;
	private boolean locked = false;
	private Thread currentThread; 
	private Object semaphore = new Object();
	
	public boolean isLocked() {
		return locked;
	}
	
	public void unlock() {
		if (!locked) {
			throw new IllegalMutexUnlocking("Cannot unlock mutex that is not locked.");
		}
		
		if (Thread.currentThread() != currentThread) {
			throw new IllegalMutexUnlocking("Cannot unlock mutex from another thread.");
		}
		
		if (waitingThreads == 0) {
			locked = false;
			currentThread = null;
		} else {
			waitingThreads--;
			synchronized (semaphore) {
				semaphore.notify();
			}
		}
	}
	
	public void lock() {
		if (locked) {
			waitingThreads++;

			try {
				synchronized (semaphore) {
					semaphore.wait();
				}
			} catch (InterruptedException e) {
				// Ignore.
			}
		} else {
			locked = true;
		}
		
		currentThread = Thread.currentThread();
	}

	public int getWaitingThreads() {
		return waitingThreads;
	}
}
