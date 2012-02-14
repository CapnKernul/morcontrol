package com.bhrobotics.morcontrol.util.concurrent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.AssertionCounter;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.concurrent.BlockingQueue;

public class BlockingQueueTest extends TestCase {
	private Object obj = new Object();
	private BlockingQueue queue = new BlockingQueue();
	
	@Test
	public void testPut() {
		final AssertionCounter counter = newCounter(150);
		Runnable putRunnable = new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					queue.put(obj);
					counter.countDown();
				}
			}
		};
		
		doAsync(putRunnable);
		doAsync(putRunnable);
		doAsync(putRunnable);
		counter.await();
		
		Object[] items = queue.toArray();
		assertThat(items.length, is(150));
	}
	
	@Test
	public void testPoll() {
		assertThat(queue.poll(), is(nullValue()));
		queue.put(obj);
		assertThat(queue.poll(), is(obj));
		assertThat(queue.poll(), is(nullValue()));
	}

	@Test
	public void testTake() {
		doAsync(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					queue.put(obj);
				}
			}
		});
		
		final AssertionCounter counter = newCounter(50);
		doAsync(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					assertThat(queue.take(), is(obj));
					counter.countDown();
				}
			}
		});
		
		counter.await();
	}
	
	@Test
	public void testIsEmpty() {
		assertThat(queue.isEmpty(), is(true));
		queue.put(obj);
		assertThat(queue.isEmpty(), is(false));
		queue.take();
		assertThat(queue.isEmpty(), is(true));
	}
	
	@Test
	public void testClear() {
		queue.put(obj);
		queue.clear();
		assertThat(queue.isEmpty(), is(true));
	}
}
