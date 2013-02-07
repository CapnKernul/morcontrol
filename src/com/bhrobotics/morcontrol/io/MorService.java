/*package com.bhrobotics.morcontrol.io;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.Robot;
import com.bhrobotics.morcontrol.devices.DeviceRegistry;
import com.bhrobotics.morcontrol.devices.input.Encoder;

public class MorService implements DeviceTransport.Iface {
    private Robot robot;
    private DeviceRegistry registry;
    
    public MorService(Robot robot) {
	this.robot = robot;
	registry = robot.getDeviceRegistry();
    }
    
    public RobotMode getMode() throws TException {
	if(robot.getMode() == com.bhrobotics.morcontrol.RobotMode.AUTONOMOUS) {
	    return RobotMode.AUTONOMOUS;
	} else if(robot.getMode() == com.bhrobotics.morcontrol.RobotMode.DISABLED) {
	    return RobotMode.DISABLED;
	} else {
	    return RobotMode.OPERATOR_CONTROL;
	}
    }

    public void initializeEncoder(Address address, Address addressOne,
	    Address addressTwo) throws InvalidAddressException, TException {
	    try {
		registry.initializeEncoder(convert(address), convert(addressOne), convert(addressTwo));
	    } catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
		throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	    }
    }

    public void updatePWM(Address address, int state)
	    throws InvalidAddressException, InvalidStateException, TException {
	try {
	    registry.getPWM(convert(address)).update(state);
	} catch (com.bhrobotics.morcontrol.devices.InvalidStateException e) {
	    throw new InvalidStateException(); 
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public void updateRelay(Address address, RelayState state)
	    throws InvalidAddressException, InvalidStateException, TException {
	try {
	    registry.getRelay(convert(address)).update(convert(state));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public void updateSolenoid(Address address, boolean state)
	    throws InvalidAddressException, TException {
	try {
	    registry.getSolenoid(convert(address)).update(state);
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public int getPWM(Address address) throws InvalidAddressException,
    TException {
	try {
	    return registry.getPWM(convert(address)).getState();
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public RelayState getRelay(Address address) throws InvalidAddressException,
    TException {
	try {
	    return convert(registry.getRelay(convert(address)).getState());
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public boolean getSolenid(Address address) throws InvalidAddressException,
    TException {
	try {
	    return registry.getSolenoid(convert(address)).getState();
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public boolean getDigitalInput(Address address)
	    throws InvalidAddressException, TException {
	try {
	    return registry.getDigitalInput(convert(address)).getState();
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public double getAnalogInput(Address address)
	    throws InvalidAddressException, TException {
	try {
	    return registry.getAnalogInput(convert(address)).getState();
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    public double getEncoder(Address addressOne, EncoderCommand command)
	    throws InvalidAddressException, InvalidCommandException, TException {
	try {
	    Encoder encoder = registry.getEncoder(convert(addressOne));
	    if(command.equals(EncoderCommand.COUNT)) {
		return encoder.getCount();
	    } else if(command.equals(EncoderCommand.DISTANCE)) {
		return encoder.getDistance();
	    } else if(command.equals(EncoderCommand.RATE)) {
		return encoder.getRate();
	    } else if(command.equals(EncoderCommand.RESET)) {
		encoder.reset();
		return 0;
	    } else {
		throw new InvalidCommandException(addressOne.toString(), command);
	    }
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw new InvalidAddressException(e.getMessage(), convert(e.getAddress()));
	}
    }

    private com.bhrobotics.morcontrol.devices.Address convert(Address address) {
	return new com.bhrobotics.morcontrol.devices.Address(address.getMod(), address.getChannel());
    }
    
    private Address convert(com.bhrobotics.morcontrol.devices.Address address) {
	return new Address(address.getModule(), address.getChannel());
    }
    
    private com.bhrobotics.morcontrol.devices.output.RelayState convert(RelayState state) throws InvalidStateException {
	if(state.equals(RelayState.FORWARD)) {
	    return com.bhrobotics.morcontrol.devices.output.RelayState.FORWARD;
	} else if(state.equals(RelayState.OFF)) {
	    return com.bhrobotics.morcontrol.devices.output.RelayState.OFF;
	} else if(state.equals(RelayState.REVERSE)){
	    return com.bhrobotics.morcontrol.devices.output.RelayState.REVERSE;
	} else {
	    throw new InvalidStateException();
	}
    }
    
    private RelayState convert(com.bhrobotics.morcontrol.devices.output.RelayState state) {
	if(state.equals(com.bhrobotics.morcontrol.devices.output.RelayState.FORWARD)) {
	    return RelayState.FORWARD;
	} else if(state.equals(com.bhrobotics.morcontrol.devices.output.RelayState.OFF)) {
	    return RelayState.OFF;
	} else {
	    return RelayState.REVERSE;
	}
    }
}*/