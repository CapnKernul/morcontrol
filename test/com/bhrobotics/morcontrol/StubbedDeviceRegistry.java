package com.bhrobotics.morcontrol;

import java.util.ArrayList;
import java.util.List;

import com.bhrobotics.morcontrol.device.output.StubbedAnalogInput;
import com.bhrobotics.morcontrol.device.output.StubbedDigitalInput;
import com.bhrobotics.morcontrol.device.output.StubbedEncoder;
import com.bhrobotics.morcontrol.device.output.StubbedPWM;
import com.bhrobotics.morcontrol.device.output.StubbedRelay;
import com.bhrobotics.morcontrol.device.output.StubbedSolenoid;
import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.DeviceRegistry;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.PWM;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.io.InvalidAddressException;

public class StubbedDeviceRegistry implements DeviceRegistry {
    private List<PWM> pwms = new ArrayList<PWM>();
    private List<Solenoid> solenoids = new ArrayList<Solenoid>();
    private List<Relay> relays = new ArrayList<Relay>();
    private List<DigitalInput> digitalInputs = new ArrayList<DigitalInput>();
    private List<AnalogInput>  analogInputs = new ArrayList<AnalogInput>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    
    
    @Override
    public PWM getPWM(Address address) {
    	for (PWM pwm : pwms) {
    		if (address.equals(pwm.getAddress())) {
    			return pwm;
    		}
    	}
    	StubbedPWM newstubbedpwm = new StubbedPWM(address);
    	pwms.add(newstubbedpwm);
   		return newstubbedpwm;
    }

    @Override
    public Relay getRelay(Address address) {
    	for (Relay relay : relays) {
    		if (address.equals(relay.getAddress())) {
    			return relay;
    		}
    	}
    	StubbedRelay newstubbedrelay = new StubbedRelay(address);
    	relays.add(newstubbedrelay);
   		return newstubbedrelay;
    }

    @Override
    public Solenoid getSolenoid(Address address) {
    	for (Solenoid solenoid : solenoids) {
    		if (address.equals(solenoid.getAddress())) {
    			return solenoid;
    		}
    	}
    	StubbedSolenoid newstubbedsolenoid = new StubbedSolenoid(address);
    	solenoids.add(newstubbedsolenoid);
   		return newstubbedsolenoid;
    }

    @Override
    public DigitalInput getDigitalInput(Address address) {
    	for (DigitalInput digitalInput : digitalInputs) {
    		if (address.equals(digitalInput.getAddress())) {
    			return digitalInput;
    		}
    	}
    	StubbedDigitalInput newdigitalinput = new StubbedDigitalInput(address);
    	digitalInputs.add(newdigitalinput);
   		return newdigitalinput;
    }

    @Override
    public AnalogInput getAnalogInput(Address address) {
    	for (AnalogInput analogInput : analogInputs) {
    		if (address.equals(analogInput.getAddress())) {
    			return analogInput;
    		}
    	}
    	StubbedAnalogInput newstubbedanaloginput = new StubbedAnalogInput(address);
    	analogInputs.add(newstubbedanaloginput);
   		return newstubbedanaloginput;
    }

    @Override
    public Encoder getEncoder(Address address) {
    	for (Encoder encoder : encoders) {
    		if (address.equals(encoder.getAddress())) {
    			return encoder;
    		}
    	}
    	StubbedEncoder newstubbedencoder = new StubbedEncoder(address);
    	encoders.add(newstubbedencoder);
   		return newstubbedencoder;
    }

	@Override
	public void initializeEncoder(Address address, Address one, Address two)
			throws InvalidAddressException {
		// TODO Auto-generated method stub
		
	}    
}
