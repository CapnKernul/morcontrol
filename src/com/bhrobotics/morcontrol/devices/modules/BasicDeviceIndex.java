package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.output.RobotSolenoid;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

public class BasicDeviceIndex {
    private Device[] devices;
    
    public BasicDeviceIndex(Registry registry, ModuleMapping mapping) {
	int moduleCount = mapping.getDigitalModuleCount();
	devices = new Device[moduleCount * registry.getChannelsPerModule()];
	for(int module = 1; module <= moduleCount; module++) {
	    for(int channel = 1; channel <= registry.getChannelsPerModule(); channel++) {
		devices[(module * channel) - 1] = registry.initializeDevice(new Address(module, channel)); 
	    }
	}
    }
    
    public Device getDeviceAt(Address address) {
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
