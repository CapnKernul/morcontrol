package com.bhrobotics.morcontrol.io;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.Robot;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.registry.AnalogInputRegistry;
import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;
import com.bhrobotics.morcontrol.devices.registry.DigitalInputRegistry;
import com.bhrobotics.morcontrol.devices.registry.EncoderRegistry;
import com.bhrobotics.morcontrol.devices.registry.PWMRegistry;
import com.bhrobotics.morcontrol.devices.registry.RelayRegistry;
import com.bhrobotics.morcontrol.devices.registry.SolenoidRegistry;

public class DeviceService implements DeviceTransport.Iface {
    private Robot robot;
    private AnalogInputRegistry analogInputs;
    private DigitalInputRegistry digitalInputs;
    private EncoderRegistry encoders;
    private PWMRegistry pwms;
    private RelayRegistry relays;
    private SolenoidRegistry solenoids;
    
    public DeviceService(Robot robot, DeviceRegistry registry) {
	this.robot = robot;
	this.analogInputs = registry.getAnalogInputs();
	this.digitalInputs = registry.getDigitalInputs();
	this.encoders = registry.getEncoders();
	this.pwms = registry.getPWMs();
	this.relays = registry.getRelays();
	this.solenoids = registry.getSolenoids();
    }

    public RobotMode getMode() throws TException {
	return Converter.convertRobotMode(robot.getMode());
    }

    public void initializeEncoder(Address address, Address addressOne, Address addressTwo) throws InvalidAddressException, TException {
	try {
	    encoders.initializeEncoder(Converter.convertAddress(address), Converter.convertAddress(address), Converter.convertAddress(address));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public void updatePWM(Address address, int state) throws InvalidAddressException, InvalidStateException, TException {
	try {
	    pwms.updatePWM(Converter.convertAddress(address), state);
	} catch (com.bhrobotics.morcontrol.devices.InvalidStateException e) {
	    throw Converter.convertInvalidStateException(e);
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public void updateRelay(Address address, RelayState state) throws InvalidAddressException, TException {
	try {
	    relays.updateRelay(Converter.convertAddress(address), Converter.convertRelayState(state));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	   throw Converter.convertInvalidAddressException(e);
	}
    }

    public void updateSolenoid(Address address, boolean state) throws InvalidAddressException, TException {
	try {
	    solenoids.updateSolenoid(Converter.convertAddress(address), state);
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public int getPWM(Address address) throws InvalidAddressException, TException {
	try {
	    return pwms.getPWM(Converter.convertAddress(address));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public RelayState getRelay(Address address) throws InvalidAddressException, TException {
	try {
	    return Converter.convertRelayState(relays.getRelay(Converter.convertAddress(address)));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public boolean getSolenid(Address address) throws InvalidAddressException, TException {
	try {
	    return solenoids.getSolenoid(Converter.convertAddress(address));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public boolean getDigitalInput(Address address) throws InvalidAddressException, TException {
	try {
	    return digitalInputs.getDigitalInput(Converter.convertAddress(address));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public double getAnalogInput(Address address) throws InvalidAddressException, TException {
	try {
	    return analogInputs.getAnalogInput(Converter.convertAddress(address));
	} catch (com.bhrobotics.morcontrol.devices.InvalidAddressException e) {
	    throw Converter.convertInvalidAddressException(e);
	}
    }

    public double getEncoder(Address addressOne, EncoderCommand command) throws InvalidAddressException, InvalidCommandException, TException {
	try {
	    Encoder encoder = ((Encoder)encoders.getDevice(Converter.convertAddress(addressOne)));
	    if(command == EncoderCommand.COUNT) {
		    return encoder.getDistance();
		} else if(command == EncoderCommand.DISTANCE) {
		    return encoder.getDistance();
		} else if(command == EncoderCommand.RATE) {
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