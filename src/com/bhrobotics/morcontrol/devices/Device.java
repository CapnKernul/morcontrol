package com.bhrobotics.morcontrol.devices;

public interface Device {

    public Address getAddress();

    public void reset();

    public DeviceType getDeviceType();

}
