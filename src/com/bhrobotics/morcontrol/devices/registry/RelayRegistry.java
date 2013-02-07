package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class RelayRegistry extends BasicDeviceRegistry {
    private BasicDeviceIndex index;
    
    public RelayRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping.getDigitalModuleCount());
	Logger.defaultLogger.debug("RelayRegistry initialized " + index.getAllDevices().length + " devices.");
    }
    
    public int getChannelsPerModule() {
	return 8;
    }

    public BasicDeviceIndex getIndex() {
	return index;
    }
    
    public Device initializeDevice(Address address) {
	return new Relay(address);
    }
    
    public Type getType() {
	return Registry.Type.RELAY;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updateRelay(Address address, RelayState state) throws InvalidAddressException {
	((Relay)index.getDeviceAt(address)).update(state);
    }
    
    public RelayState getRelay(Address address) throws InvalidAddressException {
        return ((Relay)index.getDeviceAt(address)).getState();
    }
    
    public RelayState[] getAllRelays() {
        RelayState[] states = new RelayState[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((Relay)getAllDevices()[i]).getState(); 
        }
        return states;
    }

    public void reset() {
	index.reset(); 
    }
}