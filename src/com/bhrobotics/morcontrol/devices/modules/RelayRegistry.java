package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.devices.output.RobotRelay;

public class RelayRegistry implements Registry {
    private BasicDeviceIndex index;
    
    public RelayRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping);
    }
    
    public int getChannelsPerModule() {
	return 8;
    }

    public Device initializeDevice(Address address) {
	return new RobotRelay(address);
    }
    
    public Type getType() {
	return Registry.Type.RELAY;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updateRelay(Address address, RelayState state) {
	((Relay)index.getDeviceAt(address)).update(state);
    }
    
    public RelayState getRelay(Address address) {
        return ((Relay)index.getDeviceAt(address)).getState();
    }
    
    public RelayState[] getAllRelays() {
        RelayState[] states = new RelayState[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((Relay)getAllDevices()[i]).getState(); 
        }
        return states;
    }
}