package com.bhrobotics.morcontrol.oi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.util.UnsupportedOperationException;

import com.bhrobotics.morcontrol.support.TestCase;

public class OIInputHashTest extends TestCase {
	private BooleanMessage booleanMessage = new BooleanMessage("foo", true);
	private NumberMessage numberMessage = new NumberMessage("bar", 1.0);
	private StringMessage stringMessage = new StringMessage("baz", "qux");
	private OIInputHash hash = new OIInputHash();
	
	@Test(expected=UnsupportedOperationException.class)
	public void testPut() {
		hash.put("test", "foo");
	}
	
	@Test
	public void testUpdate() {
		Message[] messages = new Message[] { booleanMessage, numberMessage, stringMessage };
		hash.update(messages);
		
		assertThat(hash.getAsBoolean("foo"), is(true));
		assertThat(hash.getAsDouble("bar"), is(1.0));
		assertThat(hash.getAsString("baz"), is("qux"));
	}
}
