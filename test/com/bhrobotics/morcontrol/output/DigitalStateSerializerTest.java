package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.output.DigitalState;
import com.bhrobotics.morcontrol.output.DigitalStateSerializer;
import com.bhrobotics.morcontrol.output.InvalidStateException;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;

public class DigitalStateSerializerTest extends TestCase {
	private DigitalStateSerializer serializer = new DigitalStateSerializer();
	private DigitalState state = DigitalState.ON;
	
	@Test
	public void testSerialize() {
		Map hash = serializer.serialize(state);
		assertThat(hash.getAsBoolean("value"), is(true));
	}

	@Test
	public void testDeserialize() {
		Map hash = new HashMap();
		hash.put("value", true);
		assertThat(serializer.deserialize(hash), is(state));
	}
	
	@Test(expected=InvalidStateException.class)
	public void testInvalidHash() {
		Map hash = new HashMap();
		serializer.deserialize(hash);
	}
}
