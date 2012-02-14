package com.bhrobotics.morcontrol.oi;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.bhrobotics.morcontrol.oi.hashes.InputHashMap;
import com.bhrobotics.morcontrol.oi.hashes.OutputHashMap;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.concurrent.Condition;

public class OperatorInterfaceTest extends TestCase {
	private OIServer server = mock(OIServer.class);
	private InputHashMap inputHash = mock(InputHashMap.class);
	private OutputHashMap outputHash = mock(OutputHashMap.class);
	private MessageConnection connection = mock(MessageConnection.class);
	private OperatorInterface oi = new OperatorInterface(server, inputHash, outputHash);
	
	@Test
	public void testConnection() {
		connect();
		assertThat(oi.getConnection(), is(connection));
		
		disconnect();
		assertThat(oi.getConnection(), is(nullValue()));
		
		connect();
		assertThat(oi.getConnection(), is(connection));
	}

	@Test
	public void testClearInputHashAfterLostConnection() {
		connect();
		disconnect();
		verify(inputHash).clear();
		
		connect();
		disconnect();
		verify(inputHash, times(2)).clear();
	}

	@Test
	public void testUpdate() {
		Message message1 = mock(Message.class);
		Message message2 = mock(Message.class);
		Message[] messages1 = new Message[] { message1 };
		Message[] messages2 = new Message[] { message2 };
		when(connection.read()).thenReturn(messages1, messages2, null);
		connect();
		
		InOrder inOrder = inOrder(inputHash);
		inOrder.verify(inputHash).update(messages1);
		inOrder.verify(inputHash).update(messages2);
	}
	
	private void connect() {
		oi.pause();
		delay(20);

		when(connection.isClosed()).thenReturn(false);
		when(server.accept()).thenReturn(connection);
		
		oi.resume();
		delay(20);
	}
	
	private void disconnect() {
		oi.pause();
		delay(20);

		when(server.accept()).thenReturn(null);
		when(connection.isClosed()).thenReturn(true);
		
		oi.resume();
		delay(20);
	}
}
