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
	private AsyncServerThread deviceThread;
	private AsyncServerThread updateThread;
	private DeviceTransport.Processor deviceProcessor;
	private UpdateTransport.Processor updateProcessor;
	private ServerSocketConnection deviceSocket;
	private ServerSocketConnection updateSocket;

	public AsyncOIServer(Robot robot, DeviceRegistry registry, Mailbox mailbox) {
		this(1515, robot, registry, mailbox);
	}

	public AsyncOIServer(int port, Robot robot, DeviceRegistry registry, Mailbox mailbox) {
		this.port = port;
		deviceProcessor = new DeviceTransport.Processor(new DeviceService(robot, registry));
		updateProcessor = new UpdateTransport.Processor(new UpdateService(mailbox));
	}

	public void start() {
		try {
			deviceSocket = (ServerSocketConnection) Connector.open("socket://:" + port);
			Logger.defaultLogger.info("Socket opened at " + port + ".");
			updateSocket = (ServerSocketConnection) Connector.open("socket://:" + (port++));
			Logger.defaultLogger.info("Socket opened at " + (port + 1) + ".");
			waitForConnection();
			
			waitFor
		} catch (IOException e) {
			Logger.defaultLogger.error("Failed to open both sockets");
		}
	}

	private void waitForConnection() {
		deviceThread = new AsyncServerThread(deviceProcessor, deviceSocket);
		deviceThread.start();
		updateThread = new AsyncServerThread(updateProcessor, updateSocket);
		updateThread.start();
		while(deviceThread.getThreadState() == ThreadState.CONNECTING && updateThread.getThreadState() == ThreadState.CONNECTING) { 
			Thread.yield(); 
		}
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
