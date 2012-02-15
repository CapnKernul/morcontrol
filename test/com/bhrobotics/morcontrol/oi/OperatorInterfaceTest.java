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
	private QueuedOIServer server = new QueuedOIServer(1234);
	private QueuedMessageConnection connection = new QueuedMessageConnection();
	private InputHashMap inputHash = mock(InputHashMap.class);
	private OutputHashMap outputHash = mock(OutputHashMap.class);
	private OperatorInterface oi = new OperatorInterface(server, inputHash, outputHash);
	
	@Test
	public void testConnection() {
		assertThat(oi.isClosed(), is(true));

		connect();
		assertThat(oi.isClosed(), is(false));
		
		disconnect();
		assertThat(oi.isClosed(), is(true));
		
		connect();
		assertThat(oi.isClosed(), is(false));
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
	public void testResendOutputHashAfterConnection() {
		connect();
		verify(outputHash).resend();
		
		disconnect();		
		connect();
		verify(outputHash, times(2)).resend();
	}

	@Test
	public void testUpdate() {
		Message message1 = mock(Message.class);
		Message message2 = mock(Message.class);
		Message[] messages1 = new Message[] { message1 };
		Message[] messages2 = new Message[] { message2 };
		connection.putReadMessages(messages1);
		connection.putReadMessages(messages2);
		
		connect();
		oi.update();
		oi.update();
		
		InOrder inOrder = inOrder(inputHash);
		inOrder.verify(inputHash).update(messages1);
		inOrder.verify(inputHash).update(messages2);
	}
	
	private void connect() {
		connection.reopen();
		server.putConnection(connection);
		oi.requireConnection();
	}
	
	private void disconnect() {
		connection.close();
		oi.verifyConnection();
	}
}
