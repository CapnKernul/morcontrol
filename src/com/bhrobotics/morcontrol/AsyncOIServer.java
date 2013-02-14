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
	private Vector readyThreads = new Vector();

	private ServerSocketConnection socketDevice;
	private ServerSocketConnection socketUpdate;

	public AsyncOIServer(TProcessor processorDevice, TProcessor processorUpdate, int port) {
		try {
			socketDevice = (ServerSocketConnection) Connector.open("socket://:" + port);
			int portTwo = 2 * port;
			socketUpdate = (ServerSocketConnection) Connector.open("socket://:" + (portTwo));
			Logger.defaultLogger.info("Sucessfully openned sockets");
		} catch (IOException e) {
			Logger.defaultLogger.error("could not open sockets");
		}
	}

	public void start() {
		
	}

	public void stop() {
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
}