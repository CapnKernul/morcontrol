package com.bhrobotics.morcontrol;

import java.util.Enumeration;
import java.util.Vector;

public class AsyncOIServer implements OIServer {

    private Vector observers = new Vector();

    private class UpdateThread implements Runnable {
	
	public void run() {
	    
	}
	
    }
    
    private class DeviceThread implements Runnable {

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
