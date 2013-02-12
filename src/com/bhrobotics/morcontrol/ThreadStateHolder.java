package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;

public class ThreadStateHolder {
    private ThreadState state = ThreadState.CONNECTING;
    private ReentrantLock lock = new ReentrantLock();
    
    public ThreadStateHolder() {
    }

    public ThreadState getState() {
	return state;
    }

    public void setState(ThreadState state) {
	lock.lock();
	this.state = state;
	lock.unlock();
    }
    
}
