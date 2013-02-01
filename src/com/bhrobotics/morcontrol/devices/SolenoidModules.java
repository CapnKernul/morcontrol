package com.bhrobotics.morcontrol.devices;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.output.RobotSolenoid;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.logger.Logger;

import edu.wpi.first.wpilibj.communication.ModulePresence;

public class SolenoidModules {
    private static final Logger logger = Logger.defaultLogger;
    private static final int MAX_MODULES = 2;
    private static final int SOLENOIDS = 8;

    private static SolenoidModules instance;

    private final Map solenoids = new HashMap();

    public static SolenoidModules getInstance() {
	if (instance == null) {
	    instance = new SolenoidModules();
	}

	return instance;
    }

    private SolenoidModules() {		
	for (int i = 0; i < MAX_MODULES; i++) {
	    if (ModulePresence.getModulePresence(ModulePresence.ModuleType.kSolenoid, i)) {
		initializeSolenoids(i + 1);
		logger.info("Initialized solenoid module " + (i + 1) + ".");
	    }
	}
    }

    public Solenoid getSolenoid(Address address) {
	return (Solenoid) solenoids.fetch(address);
    }

    public Enumeration solenoids() {
	return solenoids.values();
    }

    private void initializeSolenoids(int module) {
	for (int channel = 1; channel <= SOLENOIDS; channel++) {
	    Address address = new Address(module, channel);
	    Solenoid device = new RobotSolenoid(address);
	    solenoids.put(address, device);
	}
    }
}