package com.bhrobotics.morcontrol.oi.messages;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessageSerializer;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.MessageSerializer;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.NumberMessageSerializer;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessageSerializer;
import com.bhrobotics.morcontrol.support.TestCase;

public class MessageSerializerTest extends TestCase {
	private BooleanMessageSerializer booleanSerializer = mock(BooleanMessageSerializer.class);
	private NumberMessageSerializer numberSerializer = mock(NumberMessageSerializer.class);
	private StringMessageSerializer stringSerializer = mock(StringMessageSerializer.class);

	private BooleanMessage booleanMessage = mock(BooleanMessage.class);
	private NumberMessage numberMessage = mock(NumberMessage.class);
	private StringMessage stringMessage = mock(StringMessage.class);
	
	private MessageSerializer serializer = new MessageSerializer(booleanSerializer, numberSerializer, stringSerializer);

	@Test
	public void testSerialize() {
		when(booleanSerializer.serialize(booleanMessage)).thenReturn(new byte[] { 0 });
		when(numberSerializer.serialize(numberMessage)).thenReturn(new byte[] { 1 });
		when(stringSerializer.serialize(stringMessage)).thenReturn(new byte[] { 2 });

		assertThat(serializer.serialize((Message) booleanMessage), is(new byte[] { 0 }));
		assertThat(serializer.serialize((Message) numberMessage), is(new byte[] { 1 }));
		assertThat(serializer.serialize((Message) stringMessage), is(new byte[] { 2 }));
	}
	
	@Test
	public void testDeserialize() {
		when(booleanSerializer.canDeserialize(new byte[] { 0 })).thenReturn(true);
		when(booleanSerializer.canDeserialize(new byte[] { 1 })).thenReturn(false);
		when(booleanSerializer.canDeserialize(new byte[] { 2 })).thenReturn(false);
		
		when(numberSerializer.canDeserialize(new byte[] { 0 })).thenReturn(false);
		when(numberSerializer.canDeserialize(new byte[] { 1 })).thenReturn(true);
		when(numberSerializer.canDeserialize(new byte[] { 2 })).thenReturn(false);

		when(stringSerializer.canDeserialize(new byte[] { 0 })).thenReturn(false);
		when(stringSerializer.canDeserialize(new byte[] { 1 })).thenReturn(false);
		when(stringSerializer.canDeserialize(new byte[] { 2 })).thenReturn(true);
		
		when(booleanSerializer.deserialize(new byte[] { 0 })).thenReturn(booleanMessage);
		when(numberSerializer.deserialize(new byte[] { 1 })).thenReturn(numberMessage);
		when(stringSerializer.deserialize(new byte[] { 2 })).thenReturn(stringMessage);
		
		assertThat(serializer.deserialize(new byte[] { 0 }), is((Message) booleanMessage));
		assertThat(serializer.deserialize(new byte[] { 1 }), is((Message) numberMessage));
		assertThat(serializer.deserialize(new byte[] { 2 }), is((Message) stringMessage));
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeUnknownMessageType() {
		when(booleanSerializer.canDeserialize(new byte[] { 0 })).thenReturn(false);
		when(numberSerializer.canDeserialize(new byte[] { 1 })).thenReturn(false);
		when(stringSerializer.canDeserialize(new byte[] { 2 })).thenReturn(false);
		serializer.deserialize(new byte[] { 0 });
	}
}
