package com.bhrobotics.morcontrol.devices.tracking;

import java.util.Enumeration;
import com.bhrobotics.morcontrol.devices.tracking.DeviceObserver;
import java.util.Vector;

import com.bhrobotics.morcontrol.devices.Device;

public class BasicObservable implements Observable {

    private Vector observers = new Vector();

    public Enumeration getObservers() {
	return observers.elements();
    }

    public void addObserver(DeviceObserver observer) {
	observers.addElement(observer);
    }

    public void alertObservers(Device device) {
	Enumeration e = getObservers();
	while (e.hasMoreElements()) {
	    DeviceObserver tockable = (DeviceObserver) e.nextElement();
	    tockable.call(device);
	}
    }
}
