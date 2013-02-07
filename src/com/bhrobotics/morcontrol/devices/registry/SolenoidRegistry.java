package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class SolenoidRegistry extends BasicDeviceRegistry {
    private BasicDeviceIndex index;
    
    public SolenoidRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping.getSolenoidModuleCount());
	Logger.defaultLogger.debug("SolenoidRegistry Iinitialized " + index.getAllDevices().length + " devices.");
    }
    
    public int getChannelsPerModule() {
	return 10;
    }

    public BasicDeviceIndex getIndex() {
	return index;
    }
    
    public Device initializeDevice(Address address) {
	return new Solenoid(address);
    }
    
    public Type getType() {
	return Registry.Type.SOLENOID;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updateSolenoid(Address address, boolean state) throws InvalidAddressException {
	((Solenoid)index.getDeviceAt(address)).update(state);
    }
    
    public boolean getSolenoid(Address address) throws InvalidAddressException {
        return ((Solenoid)index.getDeviceAt(address)).getState();
    }
    
    public boolean[] getAllSolenoids() {
        boolean[] states = new boolean[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((Solenoid)getAllDevices()[i]).getState(); 
        }
        return states;
    }

    public void reset() {
	index.reset(); 
    }
}