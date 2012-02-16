package com.bhrobotics.morcontrol.output;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.output.InvalidStateException;
import com.bhrobotics.morcontrol.output.RelayState;
import com.bhrobotics.morcontrol.output.RelayStateSerializer;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;

public class RelayStateSerializerTest extends TestCase {
	private RelayStateSerializer serializer = new RelayStateSerializer();
	private RelayState forwardState = RelayState.FORWARD;
	
	@Test
	public void testSerialize() {
		Map hash = serializer.serialize(forwardState);
		assertThat(hash.getAsString("value"), is("forward"));
	}

	@Test
	public void testDeserialize() {
		Map hash = new HashMap();
		hash.put("value", "forward");
		assertThat(serializer.deserialize(hash), is(RelayState.FORWARD));
	}
	
	@Test(expected=InvalidStateException.class)
	public void testInvalidHash() {
		Map hash = new HashMap();
		serializer.deserialize(hash);
	}
}
