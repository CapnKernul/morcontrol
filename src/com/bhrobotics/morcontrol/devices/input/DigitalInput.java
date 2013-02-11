package com.bhrobotics.morcontrol.devices.input;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.devices.tracking.BasicObservable;
import com.bhrobotics.morcontrol.devices.tracking.DeviceObserver;
import com.bhrobotics.morcontrol.devices.tracking.Observable;
import com.bhrobotics.morcontrol.devices.tracking.Tickable;

public class DigitalInput implements Device, Observable, Tickable {
    private Address address;
    private edu.wpi.first.wpilibj.DigitalInput input;
    private BasicObservable observable = new BasicObservable();
    private boolean savedState = false;

    public DigitalInput(Address address) {
	this.address = address;
	input = new edu.wpi.first.wpilibj.DigitalInput(address.getModule(), address.getChannel());
    }

    public Address getAddress() {
	return address;
    }

    public boolean getState() {
	return input.get();
    }

    public edu.wpi.first.wpilibj.DigitalInput getRawDevice() {
	return input;
    }

    public void reset() {
	// Has no reseted state
    }

    public DeviceType getDeviceType() {
	return DeviceType.DIGITAL_INPUT;
    }
    
    public void tick() {
	if(savedState != getState()) {
	    savedState = getState();
	    observable.alertObservers(this);
	}
    }

    //Delegated Methods
    public void addObserver(DeviceObserver observer) {
	observable.addObserver(observer);
    }

    public Enumeration getObservers() {
	return observable.getObservers();
    }
}