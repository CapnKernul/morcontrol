package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.RobotPWM;

public class PWMRegistry implements Registry {
    private BasicDeviceIndex index;

    public int getChannelsPerModule() {
	return 10;
    }

    public Device initializeDevice(Address address) {
	return new RobotPWM(address);
    }
    
    public PWMRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping);
    }
    
    public Type getType() {
	return Registry.Type.PWM;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updatePWM(Address address, int state) throws InvalidStateException {
	((PWM)index.getDeviceAt(address)).update(state);
    }
    
    public double getPWM(Address address) {
        return ((PWM)index.getDeviceAt(address)).getState();
    }
    
    public double[] getAllPWMs() {
        double[] states = new double[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((PWM)getAllDevices()[i]).getState(); 
        }
        return states;
    }
}