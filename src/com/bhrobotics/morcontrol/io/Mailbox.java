package com.bhrobotics.morcontrol.io;

import java.util.Vector;

import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;

public class Mailbox {
	private Vector mailbox = new Vector();
	private ReentrantLock lock = new ReentrantLock();

	public void addEvent(Event event) {
		lock.lock();
		mailbox.addElement(event);
		lock.unlock();
	}

	public Event shift() {
		lock.lock();
		Event event = (Event) mailbox.remove(0);
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
}
