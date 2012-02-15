package com.bhrobotics.morcontrol.util.concurrent;

import java.util.Vector;

import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;

public class ReentrantMutex {
	private Vector waitingThreads = new Vector();
	private int acquires = 0;
	private boolean locked = false;
	private Object mutex = new Object();
	private Thread currentThread; 
	
	public boolean isLocked() {
		synchronized (mutex) {
			return locked;
		}
	}
	
	public void unlock() {
		synchronized (mutex) {
			if (!locked) {
				throw new IllegalMutexUnlocking("Cannot unlock mutex that is not locked.");
			}
			
			if (!ownsLock()) {
				throw new IllegalMutexUnlocking("Cannot unlock mutex from another thread.");
			}

			acquires--;
			if (acquires == 0) {
				relinquishLock();
			}
		}
	}
	
	public void lock() {
		synchronized (mutex) {
			try {
				if (!locked) {
					giveLock(Thread.currentThread());
				} else if (ownsLock()) {
					acquires++;
				} else {
					waitingThreads.addElement(Thread.currentThread());
					while (!ownsLock()) {
						mutex.wait();
					}
				}
			} catch (InterruptedException e) {
				throw new RuntimeInterruptedException(e);
			}
		}
	}

	public int getWaitingThreads() {
		synchronized (mutex) {
			return waitingThreads.size();			
		}
	}
	
	private boolean ownsLock() {
		return ownsLock(Thread.currentThread());
	}
	
	public boolean ownsLock(Thread thread) {
		synchronized (mutex) {
			return currentThread != null && currentThread.equals(thread);
		}
	}
	
	private Thread takeThread() {
		synchronized (mutex) {
			Thread result = (Thread) waitingThreads.firstElement();
			waitingThreads.removeElementAt(0);
			return result;
		}
	}
	
	private void giveLock(Thread thread) {
		synchronized (mutex) {
			acquires = 1;
			currentThread = thread;
			locked = true;
		}
	}
	
	private void relinquishLock() {
		synchronized (mutex) {
			if (waitingThreads.isEmpty()) {
				currentThread = null;
				locked = false;
			} else {
				giveLock(takeThread());
				mutex.notifyAll();
			}
		}
	}
}
