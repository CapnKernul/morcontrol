package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class PWMRegistry extends BasicDeviceRegistry {
    private BasicDeviceIndex index;

    public PWMRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping.getDigitalModuleCount());
	Logger.defaultLogger.debug("RelayRegistry initialized " + index.getAllDevices().length + " devices.");
    }
    
    public int getChannelsPerModule() {
	return 10;
    }
    
    public BasicDeviceIndex getIndex() {
	return index;
    }
    
    public Device initializeDevice(Address address) {
	return new PWM(address);
    }
    
    public Type getType() {
	return Registry.Type.PWM;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public void updatePWM(Address address, int state) throws InvalidStateException, InvalidAddressException {
	((PWM)index.getDeviceAt(address)).update(state);
    }
    
    public double getPWM(Address address) throws InvalidAddressException {
        return ((PWM)index.getDeviceAt(address)).getState();
    }
    
    public double[] getAllPWMs() {
        double[] states = new double[getAllDevices().length];
        for(int i = 0; i < getAllDevices().length; i++) {
 	   states[i] = ((PWM)getAllDevices()[i]).getState(); 
        }
        return states;
    }

    public void reset() {
	index.reset();
    }
}