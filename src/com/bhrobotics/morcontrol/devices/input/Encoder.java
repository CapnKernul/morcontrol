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

public class Encoder implements Device, Observable, Tickable {

	private edu.wpi.first.wpilibj.Encoder encoder;
	private Address address;
	private BasicObservable observable = new BasicObservable();
	private int savedCountState = 0;

	public Encoder(Address encoderAddress, DigitalInput first, DigitalInput second) {
		encoder = new edu.wpi.first.wpilibj.Encoder(first.getRawDevice(), second.getRawDevice());
		address = encoderAddress;
		Logger.defaultLogger.info(this.getClass().toString() + " initialized at " + address.toString());
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

	public DeviceType getDeviceType() {
		return DeviceType.ENCODER;
	}

	public void tick() {
		if (savedCountState != getCount()) {
			savedCountState = getCount();
			observable.alertObservers(this);
		}
	}

	// Delegated Methods
	public void addObserver(DeviceObserver observer) {
		observable.addObserver(observer);
	}

	public Enumeration getObservers() {
		return observable.getObservers();
	}
}
