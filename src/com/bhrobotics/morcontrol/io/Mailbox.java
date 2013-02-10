package com.bhrobotics.morcontrol.io;

import java.util.Vector;

import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.tracking.DeviceObserver;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;

public class Mailbox implements DeviceObserver {
	private Vector mailbox = new Vector();
	private ReentrantLock lock = new ReentrantLock();

	public void addEvent(Event event) {
		lock.lock();
		mailbox.addElement(event);
		lock.unlock();
	}

	public Event shift() {
		lock.lock();
		Event event = (Event)mailbox.remove(0);
		lock.unlock();
		return event;
	}

	public void clear() {
		lock.lock();
		mailbox.removeAllElements();
		lock.unlock();
	}

	public boolean isEmpty() {
		return mailbox.isEmpty();
	}

	public void call(Device device) {
		addEvent(new Event(Converter.convertDeviceType(device.getDeviceType()), Converter.convertAddress(device.getAddress())));
	}
}
