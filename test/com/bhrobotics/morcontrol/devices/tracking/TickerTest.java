package com.bhrobotics.morcontrol.devices.tracking;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
/*Ff*/
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TickerTest {
    Ticker ticker;

    @Before
    public void setUp() throws Exception {
	ticker = new Ticker();
    }

    @After
    public void tearDown() throws Exception {
	ticker.kill();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    fail("Main thread interrupted while waiting");
	}
	System.out.println(Thread.activeCount());
    }

    @Test
    public void testTicker() {
	assertThat(ticker, is(instanceOf(Ticker.class)));
    }

    @Test
    public void testAddTickable() {
	Tickable tickable = mock(Tickable.class);
	ticker.addTickable(tickable);
	ticker.tick();
	verify(tickable, atLeastOnce()).tick();

    }

    @Test
    public void testRemoveTickable() {
	Tickable tickable = mock(Tickable.class);
	ticker.addTickable(tickable);
	ticker.removeTickable(tickable);
	ticker.tick();
	verify(tickable, never()).tick();

    }

    @Test
    public void testKill() {
	int startCount = Thread.activeCount();
	ticker.kill();
	assertThat(ticker.isStopped(), is(true));
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    fail("Main thread interrupted while waiting");
	}
	assertThat(Thread.activeCount(), is(startCount - 1));
    }

    @Test
    public void testStart() {
	Tickable tickable = mock(Tickable.class);
	ticker.start();
	ticker.addTickable(tickable);
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    fail("Main thread interrupted while waiting");
	}
	verify(tickable, atLeastOnce()).tick();
    }

    @Test
    public void testStop() {
	Tickable tickable = mock(Tickable.class);
	ticker.stop();
	ticker.addTickable(tickable);
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    fail("Main thread interrupted while waiting");
	}
	verify(tickable, never()).tick();
    }
}