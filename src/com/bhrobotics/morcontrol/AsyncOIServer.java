package com.bhrobotics.morcontrol;

import java.util.Enumeration;
import java.util.Vector;

public class AsyncOIServer implements OIServer {

	private Vector observers = new Vector();

	private static class ServiceThread implements Runnable {
		private  Processor processor
		
		public ServiceThread(Processor processor)
		
		public void run() {
			
		}

	}
	
	public AsyncOIServer() {
		
	}

	public void start() {

	}

	public void stop() {

	}

	public boolean isRunning() {
		return false;
	}

	public void addObserver(OIServerObserver observer) {
		observers.addElement(observer);
	}

	public void removeObserver(OIServerObserver observer) {
		observers.remove(observer);
	}

	public Enumeration getObservers() {
		return observers.elements();
	}
}
