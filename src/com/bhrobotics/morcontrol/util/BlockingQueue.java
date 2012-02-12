package com.bhrobotics.morcontrol.util;

import java.util.Vector;

public class BlockingQueue {
	private Vector vector = new Vector();
	private ReentrantMutex vectorMutex = new ReentrantMutex();
	private Condition notEmpty = new Condition();
	
	public void put(Object obj) {
		vectorMutex.lock();
		vector.addElement(obj);
		notEmpty.signal();
		vectorMutex.unlock();
	}
	
	public Object poll() {
		vectorMutex.lock();
		Object result = null;
		
		if (!vector.isEmpty()) {
			result = vector.firstElement();
			vector.removeElementAt(0);
		}
		
		vectorMutex.unlock();
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
		vectorMutex.lock();
		vector.removeAllElements();
		vectorMutex.unlock();
	}
}
