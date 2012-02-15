package com.bhrobotics.morcontrol.oi;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class AutomaticMessageConnection implements MessageConnection {
	private ReentrantMutex mutex = new ReentrantMutex();
	private Vector observers = new Vector();
	private OIServer server;
	private MessageConnection connection;
	
	public AutomaticMessageConnection(OIServer server) {
		this.server = server;
	}
	
	public void registerObserver(AutomaticMessageConnectionObserver observer) {
		observers.addElement(observer);
	}

	public void unregisterObserver(AutomaticMessageConnectionObserver observer) {
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
		try {
			Message[] result = requireConnection().read();
			readMessages();
			return result;
		} catch (OIException e) {
			exceptionWhileReading(e);
			return null;
		}
	}
	
	public void write(Message[] messages) {
		try {
			requireConnection().write(messages);
			wroteMessages();
		} catch (OIException e) {
			exceptionWhileWriting(e);
		}
	}
	
	private void openedConnection() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.openedConnection();
		}
	}

	private void closedConnection() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.closedConnection();
		}
	}

	private void readMessages() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.readMessages();
		}
	}

	private void wroteMessages() {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.wroteMessages();
		}
	}

	private void exceptionWhileReading(OIException e) {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.exceptionWhileReading(e);
		}
	}

	private void exceptionWhileWriting(OIException e) {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			AutomaticMessageConnectionObserver observer = (AutomaticMessageConnectionObserver) enumeration.nextElement();
			observer.exceptionWhileWriting(e);
		}
	}
}
