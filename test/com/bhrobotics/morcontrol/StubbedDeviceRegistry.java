package com.bhrobotics.morcontrol;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.DeviceRegistry;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;

import edu.wpi.first.wpilibj.DigitalInput;

public class StubbedDeviceRegistry implements DeviceRegistry {
    List<PWM> pwms = new ArrayList<PWM>();
    List<Solenoid> solenoid = new ArrayList<Solenoid>();
    List<Relay> relays = new ArrayList<Relay>();
    List<DigitalInput> digitalInputs = new ArrayList<DigitalInput>();
    List<AnalogInput>  analogInputs = new ArrayList<AnalogInput>();
    List<Encoder> encoders = new ArrayList<Encoder>();
    
    
    @Override
    public PWM getPWM(Address address) {
	return null;
    }

    @Override
    public Relay getRelay(Address address) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Solenoid getSolenid(Address address) throws TException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public DigitalInput getDigitalInput(Address address) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AnalogInput getAnalogInput(Address address) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Encoder getEncoder(Address address) {
	// TODO Auto-generated method stub
	return null;
    }    
}
