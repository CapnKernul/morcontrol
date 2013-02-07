package com.bhrobotics.morcontrol.devices.registry;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class EncoderRegistry implements Registry {
    private DigitalInputRegistry registry;
    private HashMap encoders = new HashMap();
    private Vector usedDigitalInputs = new Vector();
    
    public EncoderRegistry(DigitalInputRegistry registry, ModuleMapping mapping) {
	this.registry = registry;
	Logger.defaultLogger.debug("EncoderRegistry initialized " + getAllDevices().length + " devices.");
    }
    
    public Type getType() {
	return Registry.Type.ENCODER;
    }

    public int getChannelsPerModule() {
	return 7;
    }

    public Encoder initializeEncoder(Address address, Address inputOne, Address inputTwo) throws InvalidAddressException {
	DigitalInput deviceOne = (DigitalInput)registry.getDevice(inputOne);
	DigitalInput deviceTwo = (DigitalInput)registry.getDevice(inputTwo);
	
	if(encoders.containsKey(address))
	    throw new InvalidAddressException(address);
	if(usedDigitalInputs.contains(deviceOne)) 
	   throw new InvalidAddressException(inputOne);
	if(usedDigitalInputs.contains(deviceTwo))
	    throw new InvalidAddressException(inputTwo);
	
	usedDigitalInputs.addElement(deviceOne);
	usedDigitalInputs.addElement(deviceTwo);
	Encoder encoder = new Encoder(address, deviceOne, deviceTwo);
	encoders.put(address, encoder);
	
	return encoder;
    }

    public Device getDevice(Address address) throws InvalidAddressException {
	if(encoders.containsKey(address)) {
	    return (Device)encoders.get(address);
	}
	throw new InvalidAddressException(address);
    }

    public Device[] getAllDevices() {
	Device[] devices = new Device[encoders.size()];
	Enumeration e = encoders.values();
	int count = 0;
	while(e.hasMoreElements()) {
	    devices[count] = (Device)e.nextElement();
	    count++;
	}
	return devices;
    }

    public void reset() {
	encoders.clear();
	usedDigitalInputs.removeAllElements();
    }
}
