package com.bhrobotics.morcontrol.devices.tracking;

import com.bhrobotics.morcontrol.devices.Device;

public interface DeviceObserver {
    public void call(Device device);
}
