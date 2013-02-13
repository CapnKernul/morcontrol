package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncOIServer implements OIServer {

    private Vector observers = new Vector();
    private Vector threads = new Vector();
    private Vector readyThreads = new Vector();;

    private TProcessor processorDevice;
    private TProcessor processorUpdate;

    private ServerSocketConnection socketDevice;
    private ServerSocketConnection socketUpdate;

    public AsyncOIServer(TProcessor processorDevice, TProcessor processorUpdate, int port) {
	this.processorDevice = processorDevice;
	this.processorUpdate = processorUpdate;
	try {
	    socketDevice = (ServerSocketConnection) Connector.open("socket://:" + port);
	    int portTwo = 2*port;
	    socketUpdate = (ServerSocketConnection) Connector.open("socket://:" + (portTwo));
	    Logger.defaultLogger.error("Sucessfully openned sockets");
	} catch (IOException e) {
	    Logger.defaultLogger.error("could not open sockets");
	}
    }

    private void clearAllThreads() {
	Enumeration e = threads.elements();
	while (e.hasMoreElements()) {
	    Thread thread = (Thread) e.nextElement();
	    if (thread.isAlive()) {
		thread.interrupt();
	    }
	}
	threads.removeAllElements();
	readyThreads.removeAllElements();
    }

    public void start() {
	if (threads.isEmpty()) {
	    Thread deviceThread = new AsyncThread(this, processorDevice, socketDevice, new ThreadTag(1));
	    Thread updateThread = new AsyncThread(this, processorUpdate, socketUpdate, new ThreadTag(2));
	    threads.addElement(updateThread);
	    threads.addElement(deviceThread);
	    deviceThread.start();
	    updateThread.start();
	}
    }

    public void stop() {
	clearAllThreads();
    }

    public void removeObserver(OIServerObserver observer) {
	observers.removeElement(observer);
    }

    public Enumeration getObservers() {
	return observers.elements();
    }

    public void addObserver(OIServerObserver observer) {
	observers.addElement(observer);
    }

    public void threadConnected(ThreadTag tag) {
	if (!readyThreads.contains(tag)) {
	    readyThreads.addElement(tag);
	}
    }

    public void threadDisconnected(ThreadTag tag) {
	readyThreads.removeElement(tag);
	Enumeration e = getObservers();
	while (e.hasMoreElements()) {
	    ((OIServerObserver) e.nextElement()).oiDisconnected();
	}
    }

    public boolean allConnected() {
	if (readyThreads.size() == threads.size()) {
	    Enumeration e = getObservers();
	    while (e.hasMoreElements()) {
		((OIServerObserver) e.nextElement()).oiConnected();
	    }
	    return true;
	} else {
	    return false;
	}
    }
}