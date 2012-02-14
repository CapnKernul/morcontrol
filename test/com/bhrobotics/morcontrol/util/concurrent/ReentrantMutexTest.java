package com.bhrobotics.morcontrol.util.concurrent;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.concurrent.IllegalMutexUnlocking;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class ReentrantMutexTest extends TestCase {
	private ReentrantMutex mutex = new ReentrantMutex();
	
	@Test
	public void testIsLocked() throws InterruptedException {
		assertThat(mutex.isLocked(), is(false));
		mutex.lock();
		assertThat(mutex.isLocked(), is(true));
		mutex.unlock();
		assertThat(mutex.isLocked(), is(false));
	}
	
	@Test
	public void testLockingThreads() {
		MutexThread thread1 = new MutexThread(mutex);
		MutexThread thread2 = new MutexThread(mutex);
		MutexThread thread3 = new MutexThread(mutex);
		
		assertThat(thread1.ownsLock(), is(false));
		assertThat(thread2.ownsLock(), is(false));
		assertThat(thread3.ownsLock(), is(false));
		assertThat(mutex.isLocked(), is(false));
		assertThat(mutex.getWaitingThreads(), is(0));

		thread1.lock();
		thread2.lock();
		thread3.lock();

		assertThat(thread1.ownsLock(), is(true));
		assertThat(thread2.ownsLock(), is(false));
		assertThat(thread3.ownsLock(), is(false));
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(2));
		
		thread1.unlock();
		assertThat(thread1.ownsLock(), is(false));
		assertThat(thread2.ownsLock(), is(true));
		assertThat(thread3.ownsLock(), is(false));
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(1));

		thread2.unlock();
		assertThat(thread1.ownsLock(), is(false));
		assertThat(thread2.ownsLock(), is(false));
		assertThat(thread3.ownsLock(), is(true));
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(0));

		thread3.unlock();
		assertThat(thread1.ownsLock(), is(false));
		assertThat(thread2.ownsLock(), is(false));
		assertThat(thread3.ownsLock(), is(false));
		assertThat(mutex.isLocked(), is(false));
		assertThat(mutex.getWaitingThreads(), is(0));
	}

	@Test(expected=IllegalMutexUnlocking.class)
	public void testUnlockWhenNotLocked() {
		mutex.unlock();
	}

	@Test(expected=IllegalMutexUnlocking.class)
	public void testUnlockWhenNotLockedByCurrentThread() {
		MutexThread thread = new MutexThread(mutex);
		thread.lock();
		mutex.unlock();
	}
	
	private class MutexThread extends Thread {
		private ReentrantMutex mutex;
		private final int NONE = 0;
		private final int LOCK = 1;
		private final int UNLOCK = 2;
		private int command = NONE;
		
		public MutexThread(ReentrantMutex mutex) {
			this.mutex = mutex;
			start();
		}
		
		public void run() {
			while (true) {
				switch (command) {
				case LOCK:
					mutex.lock();
					command = NONE;
					break;
				case UNLOCK:
					mutex.unlock();
					command = NONE;
					break;
				}
			}
		}
		
		public boolean ownsLock() {
			return mutex.ownsLock(this);
		}
		
		public void lock() {
			command = LOCK;
			delay(20);
		}
		
		public void unlock() {
			command = UNLOCK;
			delay(20);
		}
	}
}
