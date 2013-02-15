package com.bhrobotics.morcontrol.io;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.CompetitionRobot;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;

public class DeviceService implements DeviceTransport.Iface {

	private CompetitionRobot robot;
	private DeviceRegistry registry;

	public DeviceService(CompetitionRobot robot) {
		this.robot = robot;
		this.registry = robot.getRegistry();
	}

	public RobotMode getMode() throws TException {
		return Converter.convertRobotMode(robot.getMode());
	}

	public void initializeEncoder(Address address, Address addressOne, Address addressTwo) throws InvalidAddressException, TException {
		try {
			registry.initializeEncoder(Converter.convertAddress(address), Converter.convertAddress(address), Converter.convertAddress(address));
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public void updatePWM(Address address, int state) throws InvalidAddressException, InvalidStateException, TException {
		try {
			registry.getPWM(Converter.convertAddress(address)).update(state);
		} catch (com.bhrobotics.morcontrol.devices.InvalidStateException e) {
			throw Converter.convertInvalidStateException(e);
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public void updateRelay(Address address, RelayState state) throws InvalidAddressException, TException {
		try {
			registry.getRelay(Converter.convertAddress(address)).update(Converter.convertRelayState(state));
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public void updateSolenoid(Address address, boolean state) throws InvalidAddressException, TException {
		try {
			registry.getSolenoid(Converter.convertAddress(address)).update(state);
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public int getPWM(Address address) throws InvalidAddressException, TException {
		try {
			return registry.getPWM(Converter.convertAddress(address)).getState();
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public RelayState getRelay(Address address) throws InvalidAddressException, TException {
		try {
			return Converter.convertRelayState(registry.getRelay(Converter.convertAddress(address)).getState());
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public boolean getSolenid(Address address) throws InvalidAddressException, TException {
		try {
			return registry.getSolenoid(Converter.convertAddress(address)).getState();
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public boolean getDigitalInput(Address address) throws InvalidAddressException, TException {
		try {
			return registry.getDigital(Converter.convertAddress(address)).getState();
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public double getAnalogInput(Address address) throws InvalidAddressException, TException {
		try {
			return registry.getAnalog(Converter.convertAddress(address)).getState();
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}

	public double getEncoder(Address addressOne, EncoderCommand command) throws InvalidAddressException, InvalidCommandException, TException {
		try {
			Encoder encoder = ((Encoder) registry.getEncoder(Converter.convertAddress(addressOne)));
			if (command == EncoderCommand.COUNT) {
				return encoder.getDistance();
			} else if (command == EncoderCommand.DISTANCE) {
				return encoder.getDistance();
			} else if (command == EncoderCommand.RATE) {
				return encoder.getRate();
			} else {
				encoder.reset();
				return 0;
			}
		} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
			throw Converter.convertInvalidAddressException(e);
		}
	}
}