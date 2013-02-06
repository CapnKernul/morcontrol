package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.RobotDigitalInput;

public class DigitalInputRegistry implements Registry {
    private BasicDeviceIndex index;
    
    public DigitalInputRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping);
    }
    
    public int getChannelsPerModule() {
	return 14;
    }

    public Device initializeDevice(Address address) {
	return new RobotDigitalInput(address);
    }
    
    public Type getType() {
	return Registry.Type.DIGITAL_INPUT;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public boolean getDigitalInput(Address address) {
        return ((DigitalInput)index.getDeviceAt(address)).getState();
    }
    
    public boolean[] getAllDigitalInputs() {
        boolean[] states = new boolean[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((DigitalInput)getAllDevices()[i]).getState(); 
        }
        return states;
    }
}