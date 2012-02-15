package com.bhrobotics.morcontrol.oi.messages;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.support.TestCase;

public class NumberMessageSerializerTest extends TestCase {
	private NumberMessageSerializer serializer = new NumberMessageSerializer();
	
	@Test
	public void testSerialize() {
		byte[] bytes = serialize("foobar", 5.3);
		assertThat(bytes, is("N\037foobar\0375.3".getBytes()));
	}

	@Test
	public void testDeserialize() {
		NumberMessage message = deserialize("N\037foo\0374.2");
		assertThat(message.getKey(), is("foo"));
		assertThat(message.toDouble(), is(4.2));
	}

	@Test
	public void testCanDeserialize() {
		assertThat(serializer.canDeserialize("N\037foobar\037T".getBytes()), is(true));
		assertThat(serializer.canDeserialize("S\037foobar\037F".getBytes()), is(false));
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithoutAKey() {
		serialize("", 2.2);
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithAUnitSeparator() {
		serialize("test\037test", 2.5);
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithARecordSeparator() {
		serialize("test\036test", 2.7);
	}

	@Test(expected=OIException.class)
	public void testSerializeMessageWithAGroupSeparator() {
		serialize("test\035test", 2.7);
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAnInitialUnitSeparator() {
		deserialize("Nfoo\0374.2");
	}
		
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithAnInvalidType() {
		deserialize("D\037foo\0372.2");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAKey() {
		deserialize("N\037f\037");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAValue() {
		deserialize("N\037\0372.3");
	}

	private byte[] serialize(String key, double value) {
		NumberMessage message = new NumberMessage(key, value);
		return serializer.serialize(message);
	}
	
	private NumberMessage deserialize(String string) {
		byte[] bytes = string.getBytes();
		return serializer.deserialize(bytes);
	}
}
