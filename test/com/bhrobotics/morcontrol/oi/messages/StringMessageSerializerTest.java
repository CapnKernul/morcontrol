package com.bhrobotics.morcontrol.oi.messages;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.support.TestCase;

public class StringMessageSerializerTest extends TestCase {
	private StringMessageSerializer serializer = new StringMessageSerializer();
	
	@Test
	public void testSerialize() {
		byte[] bytes = serialize("foobar", "qux");
		assertThat(bytes, is("S\037foobar\037qux".getBytes()));
	}

	@Test
	public void testDeserialize() {
		StringMessage message = deserialize("S\037foo\037quux");
		assertThat(message.getKey(), is("foo"));
		assertThat(message.toString(), is("quux"));
	}

	@Test
	public void testCanDeserialize() {
		assertThat(serializer.canDeserialize("S\037foobar\037T".getBytes()), is(true));
		assertThat(serializer.canDeserialize("B\037foobar\037F".getBytes()), is(false));
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithoutAKey() {
		serialize("", "asdf");
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithAUnitSeparatorInTheKey() {
		serialize("test\037test", "asdf");
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithARecordSeparatorInTheKey() {
		serialize("test\036test", "asdf");
	}

	@Test(expected=OIException.class)
	public void testSerializeMessageWithAGroupSeparatorInTheKey() {
		serialize("test\035test", "asdf");
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithAUnitSeparatorInTheValue() {
		serialize("test", "test\037test");
	}
	
	@Test(expected=OIException.class)
	public void testSerializeMessageWithARecordSeparatorInTheValue() {
		serialize("test", "test\036test");
	}

	@Test(expected=OIException.class)
	public void testSerializeMessageWithAGroupSeparatorInTheValue() {
		serialize("test", "test\035test");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessagewithoutAnInitialUnitSeparator() {
		deserialize("Sfoo\037foo");
	}
	
	@Test(expected=OIException.class)
	public void testDeserializeMessageWithAnInvalidType() {
		deserialize("D\037foo\037kasdl");
	}

	@Test(expected=OIException.class)
	public void testDeserializeMessageWithoutAKey() {
		deserialize("S\037\037udjk");
	}

	private byte[] serialize(String key, String value) {
		StringMessage message = new StringMessage(key, value);
		return serializer.serialize(message);
	}
	
	private StringMessage deserialize(String string) {
		byte[] bytes = string.getBytes();
		return serializer.deserialize(bytes);
	}
}
