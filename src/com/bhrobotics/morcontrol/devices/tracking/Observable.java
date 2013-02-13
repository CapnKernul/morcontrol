package com.bhrobotics.morcontrol.devices.tracking;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.devices.Device;

public interface Observable {

    public void addObserver(DeviceObserver observer);

    public Enumeration getObservers();

}
