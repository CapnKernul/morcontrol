package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;

import edu.wpi.first.wpilibj.DigitalInput;

public class RobotEncoder implements Encoder {
    
    private edu.wpi.first.wpilibj.Encoder encoder;
    private Address address;
    
    public RobotEncoder(Address encoderAddress, DigitalInput first, DigitalInput second) {
	encoder = new edu.wpi.first.wpilibj.Encoder(first, second);
	address = encoderAddress;
    }
   
    public Address getAddress() {
	return address;
    }

    public void reset() {
	encoder.reset();
    }
    
}
