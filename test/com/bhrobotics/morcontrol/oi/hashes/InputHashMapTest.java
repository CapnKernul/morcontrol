package com.bhrobotics.morcontrol.oi.hashes;

import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.inOrder;

import com.bhrobotics.morcontrol.oi.hashes.InputHashMap;
import com.bhrobotics.morcontrol.oi.hashes.InputHashMapObserver;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.support.TestCase;

public class InputHashMapTest extends TestCase {
	private BooleanMessage booleanMessage = new BooleanMessage("foo", true);
	private NumberMessage numberMessage = new NumberMessage("bar", 1.0);
	private StringMessage stringMessage = new StringMessage("baz", "qux");
	private Message[] messages = new Message[] { booleanMessage, numberMessage, stringMessage };
	private InputHashMapObserver observer = mock(InputHashMapObserver.class);
	private InputHashMap hash = new InputHashMap();
	
	@Test
	public void testUpdate() {
		hash.update(messages);
		
		assertThat(hash.getAsBoolean("foo"), is(true));
		assertThat(hash.getAsDouble("bar"), is(1.0));
		assertThat(hash.getAsString("baz"), is("qux"));
	}
	
	@Test
	public void testClear() {
		hash.update(messages);
		hash.clear();
		
		assertThat(hash.containsKey("foo"), is(false));
		assertThat(hash.containsKey("bar"), is(false));
		assertThat(hash.containsKey("baz"), is(false));
	}
	
	@Test
	public void testObservers() {
		hash.registerObserver(observer);
		hash.update(messages);
		hash.clear();
		hash.unregisterObserver(observer);
		hash.update(messages);
		
		InOrder inOrder = inOrder(observer);
		inOrder.verify(observer).keyUpdated("foo", true);
		inOrder.verify(observer).keyUpdated("bar", 1.0);
		inOrder.verify(observer).keyUpdated("baz", "qux");
		inOrder.verify(observer).hashCleared();
	}
}
