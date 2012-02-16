package com.bhrobotics.morcontrol.output;

import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.UnknownKeyException;

public class DigitalStateSerializer {
	public Map serialize(DigitalState state) {
		Map result = new HashMap();
		if (state.equals(DigitalState.OFF)) {
			result.put("value", false);
		} else {
			result.put("value", true);
		}
		
		return result;
	}
	
	public DigitalState deserialize(Map hash) {
		try {
			boolean value = hash.fetchAsBoolean("value");
			if (value) {
				return DigitalState.ON;
			} else {
				return DigitalState.OFF;
			}
		} catch (UnknownKeyException e) {
			throw new InvalidStateException(e);
		} catch (ClassCastException e) {
			throw new InvalidStateException(e);
		}
	}
}
