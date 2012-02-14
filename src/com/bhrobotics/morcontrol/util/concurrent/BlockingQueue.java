package com.bhrobotics.morcontrol.util.concurrent;

import java.util.Vector;

public class BlockingQueue {
	private Vector vector = new Vector();
	private ReentrantMutex mutex = new ReentrantMutex();
	private Condition notEmpty = new Condition();
	
	public void put(Object obj) {
		mutex.lock();
		vector.addElement(obj);
		notEmpty.signal();
		mutex.unlock();
	}
	
	public Object poll() {
		mutex.lock();
		Object result = null;
		
		if (!vector.isEmpty()) {
			result = vector.firstElement();
			vector.removeElementAt(0);
		}
		
		mutex.unlock();
		return result;
	}
	
	public Object take() {
		Object result = null;
		
		while (result == null) {
			if (vector.isEmpty()) {
				notEmpty.await();
			}
			
			result = poll();
		}
		
		return result;
	}
	
	public boolean isEmpty() {
		return vector.isEmpty();
	}
	
	public Object[] toArray() {
		Object[] result = new Object[vector.size()];
		vector.copyInto(result);
		return result;
	}

	public void clear() {
		mutex.lock();
		vector.removeAllElements();
		mutex.unlock();
	}
}
