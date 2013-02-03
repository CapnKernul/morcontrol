package com.bhrobotics.morcontrol.devices;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.input.RobotDigitalInput;
import com.bhrobotics.morcontrol.devices.input.RobotEncoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.util.collections.HashMap;

public class RobotDeviceRegistry implements DeviceRegistry {
    private HashMap pwms = new HashMap();
    private HashMap relays = new HashMap();
    private HashMap solenoids = new HashMap();
    private HashMap digitalInputs = new HashMap();
    private HashMap analogInputs = new HashMap();
    private HashMap encoders = new HashMap();
    
    public RobotDeviceRegistry() {
	initializeHash(DigitalModules.getInstance().motors(),pwms);
	initializeHash(DigitalModules.getInstance().relays(),relays);
	initializeHash(SolenoidModules.getInstance().solenoids(),solenoids);
	initializeHash(AnalogModules.getInstance().analogInputs(),analogInputs);
	initializeHash(DigitalModules.getInstance().digitalInputs(), digitalInputs);
    }
    
    private void initializeHash(Enumeration source, HashMap destination) {
	while(source.hasMoreElements()) {
	    Device device = (Device)source.nextElement();
	    destination.put(device.getAddress(), device);
	}
    }
    
    private Device get(Address address, HashMap hash) throws InvalidAddressException {
	if(hash.containsKey(address)) {
	    return (Device)hash.fetch(address);
	} else {
	    throw new InvalidAddressException(address);
	}
    }
    
    public PWM getPWM(Address address) throws InvalidAddressException {
	return (PWM)get(address, pwms);
    }

    public Relay getRelay(Address address) throws InvalidAddressException {
	return (Relay)get(address, relays);
    }

    public Solenoid getSolenoid(Address address) throws InvalidAddressException {
	return (Solenoid)get(address, solenoids);
    }

    public DigitalInput getDigitalInput(Address address) throws InvalidAddressException {
	return (DigitalInput)get(address, digitalInputs); 
    }

    public AnalogInput getAnalogInput(Address address) throws InvalidAddressException {
	return (AnalogInput)get(address, analogInputs);
    }

    public Encoder getEncoder(Address address) throws InvalidAddressException {
	return (Encoder)get(address, encoders);
    }
    
    public void initializeEncoder(Address address, Address digitalInputOne, Address digitalInputTwo) throws InvalidAddressException {
	if(encoders.containsKey(address)) {
	    throw new InvalidAddressException(address);
	} else {
	    encoders.put(address, new RobotEncoder(address, (RobotDigitalInput)getDigitalInput(digitalInputOne), (RobotDigitalInput)getDigitalInput(digitalInputTwo)));
	}
    }
}