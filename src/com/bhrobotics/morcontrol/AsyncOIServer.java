package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncOIServer implements OIServer, OIServerObserver {

	private Vector observers = new Vector();
	private AsyncThread thread;
	private TProcessor deviceProcessor;
	private TProcessor updateProcessor;
	private ServerSocketConnection deviceSocket;
	private ServerSocketConnection updateSocket;

	private ThreadStateHolder deviceThreadState;
	private ThreadStateHolder updateThreadState;
	private MonitorThread monitorThread = null;

	public AsyncOIServer(TProcessor deviceProcessor, TProcessor updateProcessor, int port) {
		this.deviceProcessor = deviceProcessor;
		this.updateProcessor = updateProcessor;
		try {
			deviceSocket = (ServerSocketConnection) Connector.open("socket://:" + port);
			updateSocket = (ServerSocketConnection) Connector.open("socket://:" + (port + 1));
		} catch (IOException e) {
			Logger.defaultLogger.error(e.getMessage());
		}
	}

	public void start() {
		
	}

	public void stop() {
		
	}

	public void waitForConnection() {
		AsyncThread deviceThread = new AsyncThread(deviceProcessor, deviceSocket);
		AsyncThread updateThread = new AsyncThread(updateProcessor, updateSocket);
		deviceThreadState = deviceThread.getThreadStateHolder();
		updateThreadState = updateThread.getThreadStateHolder();
		while (deviceThreadState.getState() == ThreadState.CONNECTING || updateThreadState.getState() == ThreadState.CONNECTING) {
			Thread.yield();
		}
		Enumeration e = getObservers();
		while (e.hasMoreElements()) {
			((OIServerObserver) e.nextElement()).oiConnected();
		}
		deviceThreadState.setState(ThreadState.RUNNING);
		updateThreadState.setState(ThreadState.RUNNING);
	}

	public void waitForDisconnect() {
		while (deviceThreadState.getState() != ThreadState.DEAD && updateThreadState.getState() != ThreadState.DEAD) {
			Thread.yield();
		}
		Enumeration e = getObservers();
		while (e.hasMoreElements()) {
			((OIServerObserver) e.nextElement()).oiDisconnected();
		}
		deviceThreadState.setState(ThreadState.DEAD);
		updateThreadState.setState(ThreadState.DEAD);
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

	public void oiConnected() {
		
	}

	public void oiDisconnected() {
		
	}

}
