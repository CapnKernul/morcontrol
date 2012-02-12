package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.mockito.InOrder;

import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.Connection;

public class OIConnectionTest extends TestCase {
	private MessageSerializer serializer = mock(MessageSerializer.class);
	private BooleanMessage booleanMessage = mock(BooleanMessage.class);
	private NumberMessage numberMessage = mock(NumberMessage.class);
	private StringMessage stringMessage = mock(StringMessage.class);
	private Message[] messages = new Message[] { booleanMessage, numberMessage, stringMessage };
	private Connection connection = mock(Connection.class);
	private OIConnection oiConnection = new OIConnection(connection, serializer);
	
	@Test
	public void testIsClosed() throws IOException {
		when(connection.isReadable()).thenReturn(true);
		when(connection.isWritable()).thenReturn(true);
		assertThat(oiConnection.isClosed(), is(false));
		
		oiConnection.close();
		verify(connection).close();
		
		when(connection.isReadable()).thenReturn(false);
		when(connection.isWritable()).thenReturn(false);
		assertThat(oiConnection.isClosed(), is(true));
	}
	
	@Test
	public void testRead() throws IOException {
		when(connection.read()).thenReturn((byte) 0, (byte) 30, (byte) 1, (byte) 30, (byte) 2, (byte) 30, (byte) 29);
		when(serializer.deserialize(new byte[] { 0 })).thenReturn(booleanMessage);
		when(serializer.deserialize(new byte[] { 1 })).thenReturn(numberMessage);
		when(serializer.deserialize(new byte[] { 2 })).thenReturn(stringMessage);
		Message[] readMessages = oiConnection.read();
		
		assertThat(readMessages, is(messages));
	}

	@Test
	public void testWrite() throws IOException {
		when(serializer.serialize((Message) booleanMessage)).thenReturn(new byte[] { 0, 1, 2 });
		when(serializer.serialize((Message) numberMessage)).thenReturn(new byte[] { 3, 4, 5 });
		when(serializer.serialize((Message) stringMessage)).thenReturn(new byte[] { 6, 7, 8 });
		oiConnection.write(messages);
		
		InOrder inOrder = inOrder(connection);
		inOrder.verify(connection).write(new byte[] { 0, 1, 2 });
		inOrder.verify(connection).write(new byte[] { 30 });
		inOrder.verify(connection).write(new byte[] { 3, 4, 5 });
		inOrder.verify(connection).write(new byte[] { 30 });
		inOrder.verify(connection).write(new byte[] { 6, 7, 8 });
		inOrder.verify(connection).write(new byte[] { 30 });
		inOrder.verify(connection).write(new byte[] { 29 });
	}
}
