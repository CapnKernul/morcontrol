package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.mockito.InOrder;

import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.MessageSerializer;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.io.ConcurrentByteConnection;

public class ConcurrentMessageConnectionTest extends TestCase {
	private MessageSerializer serializer = mock(MessageSerializer.class);
	private BooleanMessage booleanMessage = mock(BooleanMessage.class);
	private NumberMessage numberMessage = mock(NumberMessage.class);
	private StringMessage stringMessage = mock(StringMessage.class);
	private Message[] messages = new Message[] { booleanMessage, numberMessage, stringMessage };
	private ConcurrentByteConnection byteConnection = mock(ConcurrentByteConnection.class);
	private ConcurrentMessageConnection messageConnection = new ConcurrentMessageConnection(byteConnection, serializer);
	
	@Test
	public void testIsClosed() throws IOException {
		when(byteConnection.isReadable()).thenReturn(true);
		when(byteConnection.isWritable()).thenReturn(true);
		assertThat(messageConnection.isClosed(), is(false));
		
		messageConnection.close();
		verify(byteConnection).close();
		
		when(byteConnection.isReadable()).thenReturn(false);
		when(byteConnection.isWritable()).thenReturn(false);
		assertThat(messageConnection.isClosed(), is(true));
	}
	
	@Test
	public void testRead() throws IOException {
		when(byteConnection.read()).thenReturn((byte) 0, (byte) 30, (byte) 1, (byte) 30, (byte) 2, (byte) 30, (byte) 29);
		when(serializer.deserialize(new byte[] { 0 })).thenReturn(booleanMessage);
		when(serializer.deserialize(new byte[] { 1 })).thenReturn(numberMessage);
		when(serializer.deserialize(new byte[] { 2 })).thenReturn(stringMessage);
		Message[] readMessages = messageConnection.read();
		
		assertThat(readMessages, is(messages));
	}

	@Test
	public void testWrite() throws IOException {
		when(serializer.serialize((Message) booleanMessage)).thenReturn(new byte[] { 0, 1, 2 });
		when(serializer.serialize((Message) numberMessage)).thenReturn(new byte[] { 3, 4, 5 });
		when(serializer.serialize((Message) stringMessage)).thenReturn(new byte[] { 6, 7, 8 });
		messageConnection.write(messages);
		
		InOrder inOrder = inOrder(byteConnection);
		inOrder.verify(byteConnection).write(new byte[] { 0, 1, 2 });
		inOrder.verify(byteConnection).write(new byte[] { 30 });
		inOrder.verify(byteConnection).write(new byte[] { 3, 4, 5 });
		inOrder.verify(byteConnection).write(new byte[] { 30 });
		inOrder.verify(byteConnection).write(new byte[] { 6, 7, 8 });
		inOrder.verify(byteConnection).write(new byte[] { 30 });
		inOrder.verify(byteConnection).write(new byte[] { 29 });
	}
}
