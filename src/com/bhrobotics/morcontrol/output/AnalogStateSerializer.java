package com.bhrobotics.morcontrol.output;

import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.UnknownKeyException;

public class AnalogStateSerializer {
	public Map serialize(AnalogState state) {
		Map result = new HashMap();
		result.put("value", state.toDouble());
		
		return result;
	}
	
	public AnalogState deserialize(Map hash) {
		try {
			double value = hash.fetchAsDouble("value");
			return new AnalogState(value);
		} catch (UnknownKeyException e) {
			throw new InvalidStateException(e);
		} catch (ClassCastException e) {
			throw new InvalidStateException(e);
		}
	}
}
