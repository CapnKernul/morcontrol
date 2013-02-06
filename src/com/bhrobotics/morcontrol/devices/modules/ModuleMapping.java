package com.bhrobotics.morcontrol.devices.modules;

import edu.wpi.first.wpilibj.communication.ModulePresence;
import edu.wpi.first.wpilibj.communication.ModulePresence.ModuleType;

public class ModuleMapping {
    private static ModuleMapping mapper = new ModuleMapping();
    private int ANALOG_MODULES;
    private int DIGITAL_MODULES;
    private int SOLENOID_MODULES;
    
    
    public static ModuleMapping getInstance() {
	return mapper;
    }
    
    private ModuleMapping() {
	DIGITAL_MODULES = moduleCount(ModulePresence.ModuleType.kDigital);
	ANALOG_MODULES = moduleCount(ModulePresence.ModuleType.kAnalog);
	SOLENOID_MODULES = moduleCount(ModulePresence.ModuleType.kSolenoid);
    }


    private int moduleCount(ModuleType moduleType) {
	int count = 0;
	while(ModulePresence.getModulePresence(moduleType, count + 1)) {
	    count++; 
	}
	return count;
    }
    
    public int getAnalogModuleCount() {
	return ANALOG_MODULES; 
    }
    
    public int getDigitalModuleCount() {
	return DIGITAL_MODULES;
    }
    
    public int getSolenoidModuleCount() {
	return SOLENOID_MODULES;
    }
}
