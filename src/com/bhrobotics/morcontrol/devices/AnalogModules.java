package com.bhrobotics.morcontrol.devices;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.RobotAnalogInput;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.logger.Logger;

import edu.wpi.first.wpilibj.communication.ModulePresence;

public class AnalogModules {
    private static final Logger logger = Logger.defaultLogger;
    private static final int MAX_MODULES = 2;
    private static final int ANALOGS_FOR_MODULE_1 = 7;
    private static final int ANALOGS_FOR_MODULE_2 = 8;

    private static AnalogModules instance;

    private final Map analogInputs = new HashMap();

    public static AnalogModules getInstance() {
	if (instance == null) {
	    instance = new AnalogModules();
	}

	return instance;
    }

    private AnalogModules() {
	for (int i = 0; i < MAX_MODULES; i++) {
	    if (ModulePresence.getModulePresence(ModulePresence.ModuleType.kAnalog, i)) {
		initializeAnalogInputs(i + 1);
		logger.info("Initialized analog module " + (i + 1) + ".");
	    }
	}
    }

    public AnalogInput getAnalogInput(Address address) {
	return (AnalogInput) analogInputs.fetch(address);
    }

    public Enumeration analogInputs() {
	return analogInputs.values();
    }

    private void initializeAnalogInputs(int module) {
	int channels = module == 1 ? ANALOGS_FOR_MODULE_1 : ANALOGS_FOR_MODULE_2;

	for (int channel = 1; channel <= channels; channel++) {
	    Address address = new Address(module, channel);
	    AnalogInput device = new RobotAnalogInput(address);
	    analogInputs.put(address, device);
	}
    }
}