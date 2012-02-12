package com.bhrobotics.morcontrol.util;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.Flagger;
import com.bhrobotics.morcontrol.support.TestCase;

public class ReentrantMutexTest extends TestCase {
	private ReentrantMutex mutex = new ReentrantMutex();
	
	@Test
	public void testIsLocked() {
		assertThat(mutex.isLocked(), is(false));
		mutex.lock();
		assertThat(mutex.isLocked(), is(true));
		mutex.unlock();
		assertThat(mutex.isLocked(), is(false));
	}
	
	@Test
	public void testWaitingThreads() {
		Runnable lockRunnable = new Runnable() {
			public void run() {
				mutex.lock();
				delay(10);
				mutex.unlock();
			}
		};
		
		assertThat(mutex.isLocked(), is(false));
		assertThat(mutex.getWaitingThreads(), is(0));
		
		doAsync(lockRunnable);
		doAsync(lockRunnable);
		doAsync(lockRunnable);
		
		delay(5);
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(2));
		
		delay(10);
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(1));

		delay(10);
		assertThat(mutex.isLocked(), is(true));
		assertThat(mutex.getWaitingThreads(), is(0));

		delay(10);
		assertThat(mutex.isLocked(), is(false));
		assertThat(mutex.getWaitingThreads(), is(0));
	}

	@Test
	public void testLockingThreads() {
		final Flagger flagger = new Flagger();
		
		assertThat(mutex.isLocked(), is(false));
		assertThat(mutex.getWaitingThreads(), is(0));
		
		doAsync(new Runnable() {
			public void run() {
				flagger.flag("1");
				mutex.lock();
				flagger.flag("2");
				
				delay(15);
				mutex.unlock();
				mutex.lock();
				
				flagger.flag("5");
			}
		});
		
		doAsync(new Runnable() {
			public void run() {
				delay(5);
				flagger.flag("3");
				
				mutex.lock();
				flagger.flag("4");
				
				delay(10);
				mutex.unlock();
				flagger.flag("6");
			}
		});
		
		delay(10);
		assertThat(flagger.isFlagged("1"), is(true));
		assertThat(flagger.isFlagged("2"), is(true));
		assertThat(flagger.isFlagged("3"), is(true));
		assertThat(flagger.isFlagged("4"), is(false));
		assertThat(flagger.isFlagged("5"), is(false));
		assertThat(flagger.isFlagged("6"), is(false));
		
		delay(10);
		assertThat(flagger.isFlagged("1"), is(true));
		assertThat(flagger.isFlagged("2"), is(true));
		assertThat(flagger.isFlagged("3"), is(true));
		assertThat(flagger.isFlagged("4"), is(true));
		assertThat(flagger.isFlagged("5"), is(false));
		assertThat(flagger.isFlagged("6"), is(false));

		delay(10);
		assertThat(flagger.isFlagged("1"), is(true));
		assertThat(flagger.isFlagged("2"), is(true));
		assertThat(flagger.isFlagged("3"), is(true));
		assertThat(flagger.isFlagged("4"), is(true));
		assertThat(flagger.isFlagged("5"), is(true));
		assertThat(flagger.isFlagged("6"), is(true));
	}
	
	@Test(expected=IllegalMutexUnlocking.class)
	public void testUnlockWhenNotLocked() {
		mutex.unlock();
	}

	@Test(expected=IllegalMutexUnlocking.class)
	public void testUnlockWhenNotLockedByCurrentThread() {
		doAsync(new Runnable() {
			public void run() {
				mutex.lock();
				delay(10);
			}
		});
		
		delay(5);
		mutex.unlock();
	}
}
