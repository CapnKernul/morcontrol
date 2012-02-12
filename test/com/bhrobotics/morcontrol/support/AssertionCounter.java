package com.bhrobotics.morcontrol.support;

import com.bhrobotics.morcontrol.util.Condition;

public class AssertionCounter {
	private Condition done = new Condition();
	private Counter counter;
	
	public AssertionCounter(int count) {
		counter = new Counter(count);
	}
	
	public void countDown() {
		counter.countDown();
	}
	
	public void await() {
		if (counter.isDone()) {
			return;
		}
		
		done.await();
	}
	
	private class Counter {
		private int count;
		
		public Counter(int count) {
			this.count = count;
		}
		
		public boolean isDone() {
			return count <= 0;
		}
		
		public void countDown() {
			count--;
			
			if (isDone()) {
				done.signal();
			}
		}
	}
}
