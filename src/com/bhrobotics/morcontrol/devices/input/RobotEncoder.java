package com.bhrobotics.morcontrol.devices.input;

import com.bhrobotics.morcontrol.devices.Address;

public class RobotEncoder implements Encoder {
    
    private edu.wpi.first.wpilibj.Encoder encoder;
    private Address address;
    
    public RobotEncoder(Address encoderAddress, RobotDigitalInput first, RobotDigitalInput second) {
	encoder = new edu.wpi.first.wpilibj.Encoder(first.getRawDevice(), second.getRawDevice());
	address = encoderAddress;
    }
   
    public Address getAddress() {
	return address;
    }

    public void reset() {
	encoder.reset();
    }

    public int getCount() {
	return encoder.get();
    }

    public double getRate() {
	return encoder.getRate();
    }

    public double getDistance() {
	return encoder.getDistance();
    }
    
}
