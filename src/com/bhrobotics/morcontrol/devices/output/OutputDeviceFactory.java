package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.util.OperatingSystem;

public class OutputDeviceFactory {
	public MotorDevice newMotor(int module, int channel) {
		if (OperatingSystem.isCRio()) {
			return new CRioMotorDevice(module, channel);
		} else {
			return new StubMotorDevice(module, channel);
		}
	}

	public RelayDevice newRelay(int module, int channel) {
		if (OperatingSystem.isCRio()) {
			return new CRioRelayDevice(module, channel);
		} else {
			return new StubRelayDevice(module, channel);
		}
	}

	public SolenoidDevice newSolenoid(int module, int channel) {
		if (OperatingSystem.isCRio()) {
			return new CRioSolenoidDevice(module, channel);
		} else {
			return new StubSolenoidDevice(module, channel);
		}
	}
}
