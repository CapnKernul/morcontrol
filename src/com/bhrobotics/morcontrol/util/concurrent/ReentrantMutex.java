package com.bhrobotics.morcontrol.util.concurrent;

import java.util.Vector;

import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;

public class ReentrantMutex {
	private Vector waitingThreads = new Vector();
	private Object lock = new Object();
	private boolean locked = false;
	private Thread currentThread; 
	
	public boolean isLocked() {
		synchronized (lock) {
			return locked;
		}
	}
	
	public void unlock() {
		synchronized (lock) {
			if (!locked) {
				throw new IllegalMutexUnlocking("Cannot unlock mutex that is not locked.");
			}
			
			if (getThread() != currentThread) {
				throw new IllegalMutexUnlocking("Cannot unlock mutex from another thread.");
			}

			if (waitingThreads.isEmpty()) {
				locked = false;
				currentThread = null;
			} else {
				lock.notify();
			}
		}
	}
	
	public void lock() {
		synchronized (lock) {
			try {
				if (locked) {
					waitingThreads.addElement(getThread());
					lock.wait();
					waitingThreads.removeElement(getThread());
				}
	
				locked = true;
				currentThread = getThread();
			} catch (InterruptedException e) {
				throw new RuntimeInterruptedException(e);
			}
		}
	}

	public int getWaitingThreads() {
		synchronized (lock) {
			return waitingThreads.size();			
		}
	}
	
	public boolean ownsLock(Thread thread) {
		return currentThread == thread;
	}
	
	private Thread getThread() {
		return Thread.currentThread();
	}
}
