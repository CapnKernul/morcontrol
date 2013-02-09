package com.bhrobotics.morcontrol.devices.registry;


public class DeviceRegistry {
    private ModuleMapping mapping = ModuleMapping.getInstance();
    private AnalogInputRegistry analogs = new AnalogInputRegistry(mapping);
    private DigitalInputRegistry digitals = new DigitalInputRegistry(mapping);
    private EncoderRegistry encoders = new EncoderRegistry(digitals, mapping);
    private PWMRegistry pwms = new PWMRegistry(mapping);
    private SolenoidRegistry solenoids = new SolenoidRegistry(mapping);
    private RelayRegistry relays = new RelayRegistry(mapping);

    public AnalogInputRegistry getAnalogInputs() {
	return analogs;
    }
    
    public DigitalInputRegistry getDigitalInputs() {
	return digitals;
    }
    
    public EncoderRegistry getEncoders() {
	return encoders;
    }
    
    public PWMRegistry getPWMs() {
	return pwms;
    }
    
    public SolenoidRegistry getSolenoids() {
	return solenoids;
    }
    
    public RelayRegistry getRelays() {
	return relays;
    }
    
    public ModuleMapping getMapping() {
	return mapping;
    }
}
