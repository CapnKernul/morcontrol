package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.support.AssertionCounter;
import com.bhrobotics.morcontrol.support.TestCase;

public class QueuedMessageConnectionTest extends TestCase {
	private QueuedMessageConnection connection = new QueuedMessageConnection();
	private Message message = mock(Message.class);
	private Message[] messages = new Message[] { message };
	
	@Test
	public void testRead() {
		doAsync("putter", new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					connection.putReadMessages(messages);
				}
			}
		});
		
		for (int i = 0; i < 50; i++) {
			assertThat(connection.read(), is(messages));
		}
	}

	@Test
	public void testWrite() {
		doAsync("writer", new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					connection.write(messages);
				}
			}
		});
		
		for (int i = 0; i < 50; i++) {
			assertThat(connection.takeWrittenMessage(), is(messages));
		}
	}
	
	@Test
	public void testClose() {
		assertThat(connection.isClosed(), is(false));
		connection.close();
		assertThat(connection.isClosed(), is(true));
	}
	
	@Test
	public void testCloseWhileBlockedOnReading() {
		final AssertionCounter counter = new AssertionCounter(1);
		
		doAsync(new Runnable() {
			public void run() {
				try {
					connection.read();
				} catch (OIException e) {
					counter.countDown();
				}
			}
		});
		
		delay(5);
		connection.close();
		
		counter.await();
	}
	
	@Test
	public void testCloseWhileBlockedOnTakingFromTheWritingQueue() {
		final AssertionCounter counter = new AssertionCounter(1);
		
		doAsync(new Runnable() {
			public void run() {
				try {
					connection.takeWrittenMessage();
				} catch (OIException e) {
					counter.countDown();
				}
			}
		});
		
		delay(5);
		connection.close();
		
		counter.await();
	}
}
