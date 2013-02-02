package com.bhrobotics.morcontrol.devices;

import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.io.InvalidAddressException;

public interface DeviceRegistry {
    
    public PWM getPWM(Address address) throws InvalidAddressException, InvalidStateException;

    public Relay getRelay(Address address) throws InvalidAddressException;

    public Solenoid getSolenoid(Address address) throws InvalidAddressException;

    public DigitalInput getDigitalInput(Address address) throws InvalidAddressException;

    public AnalogInput getAnalogInput(Address address) throws InvalidAddressException;

    public Encoder getEncoder(Address address) throws InvalidAddressException;
    
    public void initializeEncoder(Address address, Address one, Address two) throws InvalidAddressException;
}
