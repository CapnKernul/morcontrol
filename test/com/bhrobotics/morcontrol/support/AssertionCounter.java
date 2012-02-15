package com.bhrobotics.morcontrol.support;

import com.bhrobotics.morcontrol.util.concurrent.SingleUseCondition;

public class AssertionCounter {
	private SingleUseCondition condition = new SingleUseCondition();
	private Counter counter;
	
	public AssertionCounter(int count) {
		counter = new Counter(count);
	}
	
	public void countDown() {
		counter.countDown();
	}
	
	public void await() {
		condition.await();
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
				condition.signal();
			}
		}
	}
}
