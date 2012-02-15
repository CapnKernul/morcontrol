package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class AutomaticMessageConnectionTest extends TestCase {
	private QueuedOIServer server = new QueuedOIServer(1234);
	private MessageConnection innerConnection = mock(MessageConnection.class);
	private OIConnectionObserver observer = mock(OIConnectionObserver.class);
	private OIConnection connection = new OIConnection(server);
	
	@Test
	public void testConnection() {
		assertThat(connection.isClosed(), is(true));

		connect();
		assertThat(connection.isClosed(), is(false));
		
		disconnect();
		assertThat(connection.isClosed(), is(true));
		
		connect();
		assertThat(connection.isClosed(), is(false));
	}

	@Test
	public void testObservers() {
		connection.registerObserver(observer);
		
		connect();
		verify(observer).connectionOpened();
		disconnect();
		verify(observer).connectionClosed();
		
		connection.unregisterObserver(observer);
		
		connect();
		verify(observer).connectionOpened();
		disconnect();
		verify(observer).connectionClosed();
	}
	
	private void connect() {
		when(innerConnection.isClosed()).thenReturn(false);
		server.putConnection(innerConnection);
		connection.requireConnection();
	}
	
	private void disconnect() {
		when(innerConnection.isClosed()).thenReturn(true);
		connection.verifyConnection();
	}
}
