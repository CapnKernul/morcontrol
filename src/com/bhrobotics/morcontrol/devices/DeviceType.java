package com.bhrobotics.morcontrol.devices;

public class DeviceType {

    public static final DeviceType MOTOR = new DeviceType();
    public static final DeviceType SOLENOID = new DeviceType();
    public static final DeviceType RELAY = new DeviceType();
    public static final DeviceType ENCODER = new DeviceType();
    public static final DeviceType DIGITAL_INPUT = new DeviceType();
    public static final DeviceType ANALOG_INPUT = new DeviceType();

    private DeviceType() {
    }
}
