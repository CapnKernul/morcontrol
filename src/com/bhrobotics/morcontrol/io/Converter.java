package com.bhrobotics.morcontrol.io;

public class Converter {

	public static com.bhrobotics.morcontrol.devices.Address convertAddress(Address address) {
		return new com.bhrobotics.morcontrol.devices.Address(address.getMod(), address.getChannel());
	}

	public static Address convertAddress(com.bhrobotics.morcontrol.devices.Address address) {
		return new Address(address.getModule(), address.getChannel());
	}

	public static com.bhrobotics.morcontrol.devices.output.RelayState convertRelayState(RelayState state) {
		if (state.equals(RelayState.FORWARD)) {
			return com.bhrobotics.morcontrol.devices.output.RelayState.FORWARD;
		} else if (state.equals(RelayState.OFF)) {
			return com.bhrobotics.morcontrol.devices.output.RelayState.OFF;
		} else {
			return com.bhrobotics.morcontrol.devices.output.RelayState.REVERSE;
		}
	}

	public static RelayState convertRelayState(com.bhrobotics.morcontrol.devices.output.RelayState state) {
		if (state.equals(com.bhrobotics.morcontrol.devices.output.RelayState.FORWARD)) {
			return RelayState.FORWARD;
		} else if (state.equals(com.bhrobotics.morcontrol.devices.output.RelayState.OFF)) {
			return RelayState.OFF;
		} else {
			return RelayState.REVERSE;
		}
	}

	public static com.bhrobotics.morcontrol.RobotMode convertRobotMode(RobotMode mode) {
		if (mode == RobotMode.AUTONOMOUS) {
			return com.bhrobotics.morcontrol.RobotMode.AUTONOMOUS;
		} else if (mode == RobotMode.DISABLED) {
			return com.bhrobotics.morcontrol.RobotMode.DISABLED;
		} else {
			return com.bhrobotics.morcontrol.RobotMode.OPERATOR_CONTROL;
		}
	}

	public static RobotMode convertRobotMode(com.bhrobotics.morcontrol.RobotMode mode) {
		if (mode == com.bhrobotics.morcontrol.RobotMode.AUTONOMOUS) {
			return RobotMode.AUTONOMOUS;
		} else if (mode == com.bhrobotics.morcontrol.RobotMode.DISABLED) {
			return RobotMode.DISABLED;
		} else {
			return RobotMode.OPERATOR_CONTROL;
		}
	}

	public static InvalidStateException convertInvalidStateException(com.bhrobotics.morcontrol.devices.InvalidStateException e) {
		return new InvalidStateException(e.getMessage());
	}

	public static com.bhrobotics.morcontrol.devices.InvalidStateException convertInvalidStateException(InvalidStateException e) {
		return new com.bhrobotics.morcontrol.devices.InvalidStateException(e.getMessage());
	}

	public static InvalidAddressException convertInvalidAddressException(com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
		return new InvalidAddressException(e.getMessage(), convertAddress(e.getAddress()));
	}

	public static com.bhrobotics.morcontrol.devices.InvalidAddressException convertInvalidAddressException(InvalidAddressException e) {
		return new com.bhrobotics.morcontrol.devices.InvalidAddressException(e.getMessage(), convertAddress(e.getAddress()));
	}

	public static DeviceType convertDeviceType(com.bhrobotics.morcontrol.devices.DeviceType device) {
		if(device == com.bhrobotics.morcontrol.devices.DeviceType.ANALOG_INPUT) {
			return DeviceType.ANALOG_INPUT;
		} else if(device == com.bhrobotics.morcontrol.devices.DeviceType.DIGITAL_INPUT) {
			return DeviceType.DIGITAL_INPUT;
		} else if(device == com.bhrobotics.morcontrol.devices.DeviceType.ENCODER) {
			return DeviceType.ENCODER;
		} else {
			return DeviceType.ROBOT;
		}
	}
}
