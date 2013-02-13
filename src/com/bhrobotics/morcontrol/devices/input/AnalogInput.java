package com.bhrobotics.morcontrol.devices.input;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.devices.tracking.BasicObservable;
import com.bhrobotics.morcontrol.devices.tracking.DeviceObserver;
import com.bhrobotics.morcontrol.devices.tracking.Observable;
import com.bhrobotics.morcontrol.devices.tracking.Tickable;
import com.bhrobotics.morcontrol.util.logger.Logger;

import edu.wpi.first.wpilibj.AnalogChannel;

public class AnalogInput implements Device, Observable, Tickable {

    private Address address;
    private AnalogChannel input;
    private BasicObservable observable = new BasicObservable();
    private double savedState = 0.0;

    public AnalogInput(Address address) {
	this.address = address;
	input = new AnalogChannel(address.getModule(), address.getChannel());
	Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
    }

    public Address getAddress() {
	return address;
    }

    public double getState() {
	return input.getVoltage();
    }

    public void reset() {
	// Has no reset state
    }

    public DeviceType getDeviceType() {
	return DeviceType.ANALOG_INPUT;
    }

    public void tick() {
	if (savedState != getState()) {
	    savedState = getState();
	    observable.alertObservers(this);
	}
    }

    // Delegated methods
    public void addObserver(DeviceObserver observer) {
	observable.addObserver(observer);
    }

    public Enumeration getObservers() {
	return observable.getObservers();
    }
}
