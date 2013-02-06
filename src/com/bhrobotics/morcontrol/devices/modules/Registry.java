package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public interface Registry {
    
    public static class Type {
	public static final Type ANALOG_INPUT = new Type();
	public static final Type DIGITAL_INPUT = new Type();
	public static final Type RELAY = new Type();
	public static final Type PWM = new Type();
	public static final Type SOLENOID = new Type();
	public static final Type ENCODER = new Type();
	
	private Type() {}
    }
    
    public abstract Type getType();
    
    public int getChannelsPerModule();

    public Device initializeDevice(Address address);
}
