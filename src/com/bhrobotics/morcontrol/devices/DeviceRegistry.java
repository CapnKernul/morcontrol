package com.bhrobotics.morcontrol.devices;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.Digitalnput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

public interface DeviceRegistry {
    
    public PWM getPWM(Address address) throws TException;

    public Relay getRelay(Address address) throws TException;

    public Solenoid getSolenid(Address address) throws TException;

    public Digitalnput getDigitalInput(Address address) throws TException;

    public AnalogInput getAnalogInput(Address address) throws TException;

    public Encoder getEncoder(Address address) throws TException;

}
