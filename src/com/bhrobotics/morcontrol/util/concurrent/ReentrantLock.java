package com.bhrobotics.morcontrol.util.concurrent;

public class ReentrantLock {
    private Thread owner;
    private long count;

    public boolean isLocked() {
	return owner != null;
    }

    public boolean ownsLock(Thread thread) {
	return owner == thread;
    }

    public void lock() {
	Thread caller = Thread.currentThread();
	synchronized (this) {
	    if (caller == owner) {
		count++;
	    } else {
		try {
		    while (owner != null) {
			wait();
		    }

		    owner = caller;
		    count = 1;
		} catch (InterruptedException exception) {
		    return;
		}
	    }
	}
    }

    public void unlock() {
	synchronized (this) {
	    if (Thread.currentThread() == owner) {
		count--;

		if (count == 0) {
		    owner = null;
		    notify();
		}
	    } else {
		throw new IllegalMonitorStateException("Current thread does not hold this lock");
	    }
	}
    }
}
