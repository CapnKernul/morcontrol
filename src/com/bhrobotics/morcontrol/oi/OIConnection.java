package com.bhrobotics.morcontrol.oi;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class OIConnection implements MessageConnection {
	private ReentrantMutex mutex = new ReentrantMutex();
	private Vector observers = new Vector();
	private OIServer server;
	private MessageConnection connection;
	
	public OIConnection() {
		this(new OIServerFactory().create());
	}

	public OIConnection(int port) {
		this(new OIServerFactory().create(port));
	}
	
	public OIConnection(OIServer server) {
		this.server = server;
	}
	
	public void registerObserver(OIConnectionObserver observer) {
		observers.addElement(observer);
	}

	public void unregisterObserver(OIConnectionObserver observer) {
		observers.removeElement(observer);
	}

	public void verifyConnection() {
		mutex.lock();
		if (connection != null && connection.isClosed()) {
			connection = null;
			closedConnection();
		}
		mutex.unlock();
	}
	
	public MessageConnection requireConnection() {
		mutex.lock();
		
		if (isClosed()) {
			do {
				connection = server.accept();
			} while (isClosed());
			
			openedConnection();
		}
		
		MessageConnection result = connection;
		mutex.unlock();
		return result;
	}

	public void close() {
		mutex.lock();
		connection.close();
		mutex.unlock();
	}

	public boolean isClosed() {
		mutex.lock();
		verifyConnection();
		boolean result = connection == null;
		mutex.unlock();
		return result;
	}
	
	public Message[] read() {
		return requireConnection().read();
	}
	
	public void write(Message[] messages) {
		requireConnection().write(messages);
	}
	
	private void openedConnection() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			OIConnectionObserver observer = (OIConnectionObserver) enumeration.nextElement();
			observer.connectionOpened();
		}
	}

	private void closedConnection() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			OIConnectionObserver observer = (OIConnectionObserver) enumeration.nextElement();
			observer.connectionClosed();
		}
	}
}
