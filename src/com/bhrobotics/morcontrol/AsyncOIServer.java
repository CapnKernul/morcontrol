package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;

import org.apache.thrift.TProcessor;

import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;
import com.bhrobotics.morcontrol.io.DeviceService;
import com.bhrobotics.morcontrol.io.DeviceTransport;
import com.bhrobotics.morcontrol.io.DeviceTransport.Processor;
import com.bhrobotics.morcontrol.io.Mailbox;
import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.io.UpdateService;
import com.bhrobotics.morcontrol.io.UpdateTransport;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncOIServer implements OIServer {

	private Vector observers = new Vector();
	private int port;
	private Robot robot;
	private DeviceRegistry registry;
	private Mailbox mailbox;

	public AsyncOIServer(Robot robot, DeviceRegistry registry, TProcessor processor) {
		this(1515, robot, registry);
	}

	public AsyncOIServer(int port, Robot robot, DeviceRegistry registry, Mailbox mailbox) {
	this.mailbox = mailbox;
	TProcessor deviceProcessor = new DeviceTransport.Processor(new DeviceService(robot, registry));
	TProcessor updateProcessor = new UpdateTransport.Processor(new UpdateService(mailbox));
	try {
	    ServerSocketConnection deviceSocket = (ServerSocketConnection)Connector.open("socket://:" + port);
	    ServerSocketConnection updateSocket = (ServerSocketConnection)Connector.open("socket://:" + (port++));
	    AsyncThread deviceThread = new AsyncThread(deviceProcessor, deviceSocket);
	    AsyncThread updateThread = new AsyncThread(updateProcessor, updateSocket);
	    deviceThread.start();
	    updateThread.start();
	    
	} catch (IOException e) {
	    Logger.defaultLogger.error("Failed to open a socket.");
	}

	private waitForDisconnect() {

	}

	public void stop() {
		deviceThread.kill();
		updateThread.kill();
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
