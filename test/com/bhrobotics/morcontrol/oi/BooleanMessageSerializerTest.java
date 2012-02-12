package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class BooleanMessageSerializerTest extends TestCase {
	private BooleanMessageSerializer serializer = new BooleanMessageSerializer();
	
	@Test
	public void testSerialize() {
		byte[] bytes = serialize("foobar", true);
		assertThat(bytes, is("B\037foobar\037T".getBytes()));
	}

	@Test
	public void testDeserialize() {
		BooleanMessage message = deserialize("B\037foo\037F");
		assertThat(message.getKey(), is("foo"));
		assertThat(message.toBoolean(), is(false));
	}

	@Test
	public void testCanDeserialize() {
		assertThat(serializer.canDeserialize("B\037foobar\037T".getBytes()), is(true));
		assertThat(serializer.canDeserialize("S\037foobar\037F".getBytes()), is(false));
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithEmptyKey() {
		serialize("", true);
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithAUnitSeparator() {
		serialize("test\037test", true);
	}

	@Test(expected=OIException.class)
	public void testSerializeMessageWithARecordSeparator() {
		serialize("test\036test", true);
	}

	@Test(expected=OIException.class)
	public void testSerializeMessageWithAGroupSeparator() {
		serialize("test\035test", true);
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAnInitialUnitSeparator() {
		deserialize("Bfoo\037F");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithAnInvalidType() {
		deserialize("D\037foo\037F");
	}

	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAKey() {
		deserialize("B\037\037d");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAValue() {
		deserialize("B\037f\037");
	}

	private byte[] serialize(String key, boolean value) {
		BooleanMessage message = new BooleanMessage(key, value);
		return serializer.serialize(message);
	}
	
	private BooleanMessage deserialize(String string) {
		byte[] bytes = string.getBytes();
		return serializer.deserialize(bytes);
	}
}
