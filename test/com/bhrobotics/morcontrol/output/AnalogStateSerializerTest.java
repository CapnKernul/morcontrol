package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.output.AnalogStateSerializer;
import com.bhrobotics.morcontrol.output.InvalidStateException;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;

public class AnalogStateSerializerTest extends TestCase {
	private AnalogStateSerializer serializer = new AnalogStateSerializer();
	private AnalogState state = new AnalogState(0.0);
	
	@Test
	public void testSerialize() {
		Map hash = serializer.serialize(state);
		assertThat(hash.getAsDouble("value"), is(0.0));
	}

	@Test
	public void testDeserialize() {
		Map hash = new HashMap();
		hash.put("value", 0.0);
		assertThat(serializer.deserialize(hash), is(state));
	}
	
	@Test(expected=InvalidStateException.class)
	public void testInvalidHash() {
		Map hash = new HashMap();
		serializer.deserialize(hash);
	}
}
