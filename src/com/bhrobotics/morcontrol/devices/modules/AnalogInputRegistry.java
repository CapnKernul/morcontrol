package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.RobotAnalogInput;

public class AnalogInputRegistry implements Registry {
    private BasicDeviceIndex index;
    
    public AnalogInputRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping);
    }
    
    public int getChannelsPerModule() {
	return 8;
    }

    public Device initializeDevice(Address address) {
	return new RobotAnalogInput(address);
    }
    
    public Type getType() {
	return Registry.Type.ANALOG_INPUT;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public double getAnalogInput(Address address) {
        return ((AnalogInput)index.getDeviceAt(address)).getState();
    }
    
    public double[] getAllAnalogInputs() {
        double[] states = new double[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((AnalogInput)getAllDevices()[i]).getState(); 
        }
        return states;
    }
}