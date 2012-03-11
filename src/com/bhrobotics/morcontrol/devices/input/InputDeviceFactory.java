package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.util.OperatingSystem;

public class InputDeviceFactory {
	public DigitalInputDevice newDigitalInput(int module, int channel) {
		if (OperatingSystem.isCRio()) {
			return new CRioDigitalInputDevice(module, channel);
		} else {
			return new StubDigitalInputDevice(module, channel);
		}
	}
	
	public AnalogInputDevice newAnalogInput(int module, int channel) {
		if (OperatingSystem.isCRio()) {
			return new CRioAnalogInputDevice(module, channel);
		} else {
			return new StubAnalogInputDevice(module, channel);
		}
	}
}
