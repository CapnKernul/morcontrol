package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;

public class BasicDeviceIndex {
    private Device[] devices;
    
    public BasicDeviceIndex(BasicDeviceRegistry registry, int moduleNumber) {
	devices = new Device[moduleNumber * registry.getChannelsPerModule()];
	for(int module = 1; module <= moduleNumber; module++) {
	    for(int channel = 1; channel <= registry.getChannelsPerModule(); channel++) {
		devices[(module * channel) - 1] = registry.initializeDevice(new Address(module, channel)); 
	    }
	}
    }
    
    public Device getDeviceAt(Address address) throws InvalidAddressException {
	return getAllDevices()[address.getChannel() * address.getModule()];
    }

    public Device[] getAllDevices() {
	return devices;
    }

    public void reset() {
	for(int i = 0; i < getAllDevices().length; i++) {
	    getAllDevices()[i].reset();
	}
    }
}
