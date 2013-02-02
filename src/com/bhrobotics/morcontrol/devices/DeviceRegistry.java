package com.bhrobotics.morcontrol.devices;


import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

public interface DeviceRegistry {
    
    public PWM getPWM(Address address);

    public Relay getRelay(Address address);

    public Solenoid getSolenoid(Address address);

    public DigitalInput getDigitalInput(Address address);

    public AnalogInput getAnalogInput(Address address);

    public Encoder getEncoder(Address address);

}
