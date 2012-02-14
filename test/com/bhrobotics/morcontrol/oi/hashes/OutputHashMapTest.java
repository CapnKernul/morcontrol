package com.bhrobotics.morcontrol.oi.hashes;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.bhrobotics.morcontrol.oi.ConcurrentMessageConnection;
import com.bhrobotics.morcontrol.oi.hashes.OutputHashMap;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.support.TestCase;

public class OutputHashMapTest extends TestCase {
	private ConcurrentMessageConnection connection = mock(ConcurrentMessageConnection.class);
	private OutputHashMap hash = new OutputHashMap(connection);

	@Test
	public void testPutBoolean() {
		hash.put("foo", true);
		assertThat(hash.getAsBoolean("foo"), is(true));
		
		Message message = (Message) new BooleanMessage("foo", true);
		verify(connection).write(new Message[] { message });
	}

	@Test
	public void testPutDouble() {
		hash.put("foo", 3.9);
		assertThat(hash.getAsDouble("foo"), is(3.9));
		
		Message message = (Message) new NumberMessage("foo", 3.9);
		verify(connection).write(new Message[] { message });
	}
	
	@Test
	public void testPutString() {
		hash.put("foo", "bar");
		assertThat(hash.getAsString("foo"), is("bar"));
		
		Message message = (Message) new StringMessage("foo", "bar");
		verify(connection).write(new Message[] { message });
	}
	
	@Test
	public void testIsInTransaction() {
		assertThat(hash.isInTransaction(), is(false));
		hash.beginTransaction();
		assertThat(hash.isInTransaction(), is(true));
		hash.commitTransaction();
		assertThat(hash.isInTransaction(), is(false));
	}
	
	@Test
	public void testTransaction() {
		hash.beginTransaction();
		hash.put("foo", true);
		hash.put("bar", 2.2);
		hash.put("baz", "qux");
		hash.commitTransaction();
		
		Message message1 = (Message) new BooleanMessage("foo", true);
		Message message2 = (Message) new NumberMessage("bar", 2.2);
		Message message3 = (Message) new StringMessage("baz", "qux");
		verify(connection).write(new Message[] { message1, message2, message3 });
	}
	
	@Test
	public void testResend() {
		hash.put("bar", 2.2);
		hash.put("baz", "qux");
		hash.put("foo", true);
		hash.resend();
		
		Message message1 = (Message) new NumberMessage("bar", 2.2);
		Message message2 = (Message) new StringMessage("baz", "qux");
		Message message3 = (Message) new BooleanMessage("foo", true);
		verify(connection).write(new Message[] { message1, message2, message3 });
	}
}
