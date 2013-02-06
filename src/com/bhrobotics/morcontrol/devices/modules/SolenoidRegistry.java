package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.RobotSolenoid;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

public class SolenoidRegistry implements Registry {
    private BasicDeviceIndex index;
    
    public SolenoidRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping);
    }
    
    public int getChannelsPerModule() {
	return 10;
    }

    public Device initializeDevice(Address address) {
	return new RobotSolenoid(address);
    }
    
    public Type getType() {
	return Registry.Type.SOLENOID;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updateSolenoid(Address address, boolean state) {
	((Solenoid)index.getDeviceAt(address)).update(state);
    }
    
    public boolean getSolenoid(Address address) {
        return ((Solenoid)index.getDeviceAt(address)).getState();
    }
    
    public boolean[] getAllSolenoids() {
        boolean[] states = new boolean[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((Solenoid)getAllDevices()[i]).getState(); 
        }
        return states;
    }
}