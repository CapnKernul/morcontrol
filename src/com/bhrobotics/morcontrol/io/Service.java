package com.bhrobotics.morcontrol.io;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.Robot;
import com.bhrobotics.morcontrol.devices.input.InputDevices;
import com.bhrobotics.morcontrol.devices.output.OutputDevices;
import com.bhrobotics.morcontrol.io.MorTransport.Iface;

public class Service implements Iface {
	private final Robot robot = Robot.getInstance();
	private final OutputDevices outputDevices = OutputDevices.getInstance();
	private final InputDevices inputDevices = InputDevices.getInstance();
	
	public RobotMode getMode() throws TException {
		return toThriftRobotMode(robot.getMode());
	}

	public void updateMotor(Address address, double state) throws TException, InvalidStateException {
		try {
			outputDevices.updateMotor(fromThriftAddress(address), state);
		} catch (com.bhrobotics.morcontrol.devices.InvalidStateException e) {
			throw new InvalidStateException();
		}
	}

	public void updateRelay(Address address, RelayState state) throws TException {
		outputDevices.updateRelay(fromThriftAddress(address), fromThriftRelayState(state));
	}

	public void updateSolenoid(Address address, boolean state) throws TException {
		outputDevices.updateSolenoid(fromThriftAddress(address), state);
	}
	
	private RobotMode toThriftRobotMode(com.bhrobotics.morcontrol.RobotMode mode) {
		if (mode.equals(com.bhrobotics.morcontrol.RobotMode.OPERATOR_CONTROL)) {
			return RobotMode.OPERATOR_CONTROL;
		} else if (mode.equals(com.bhrobotics.morcontrol.RobotMode.AUTONOMOUS)) {
			return RobotMode.AUTONOMOUS;
		} else {
			return RobotMode.DISABLED;
		}
	}

	private com.bhrobotics.morcontrol.devices.output.RelayState fromThriftRelayState(RelayState state) {
		if (state.equals(RelayState.FORWARD)) {
			return com.bhrobotics.morcontrol.devices.output.RelayState.FORWARD;
		} else if (state.equals(RelayState.REVERSE)) {
			return com.bhrobotics.morcontrol.devices.output.RelayState.REVERSE;
		} else {
			return com.bhrobotics.morcontrol.devices.output.RelayState.OFF;
		}
	}
	
	private com.bhrobotics.morcontrol.devices.Address fromThriftAddress(Address address) {
		return new com.bhrobotics.morcontrol.devices.Address(address.getMod(), address.getChannel());
	}
}
