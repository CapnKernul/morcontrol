package com.bhrobotics.morcontrol.oi;

import java.util.Enumeration;
import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.bhrobotics.morcontrol.protobuf.InputUpdates;
import com.bhrobotics.morcontrol.protobuf.OutputUpdates;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;
import com.bhrobotics.morcontrol.util.io.ByteConnection;

public class OIConnection {
	private ReentrantMutex mutex = new ReentrantMutex();
	private Vector observers = new Vector();
	private boolean connecting = false;
	private OIServer server;
	private ByteConnection connection;
	
	public OIConnection() {
		this(new OIServerFactory().newServer());
	}

	public OIConnection(int port) {
		this(new OIServerFactory().newServer(port));
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
	
	public void requireConnection() {
		mutex.lock();
		if (!connecting) {
			connecting = true;
			
			if (isClosed()) {
				do {
					mutex.unlock();
					ByteConnection c = server.accept();
					mutex.lock();
					connection = c;
				} while (isClosed());
				
				openedConnection();
			}
			
			connecting = false;
		}
		mutex.unlock();
	}

	public InputStream getInputStream() {
		ByteConnection c = connection;
		
		if (c == null) {
			return null;
		} else {
			return c.getInputStream();
		}
	}

	public OutputStream getOutputStream() {
		ByteConnection c = connection;
		
		if (c == null) {
			return null;
		} else {
			return c.getOutputStream();
		}
	}
	
	public void close() {
		ByteConnection c = connection;
		if (c == null) {
			return;
		}
		
		mutex.lock();
		if (c == connection) {
			try {
				connection.close();
			} catch (IOException e) {
				// Ignore.
			} finally {
				connection = null;
				closedConnection();
				mutex.unlock();
			}
		} else {
			mutex.unlock();
		}
	}

	public boolean isClosed() {
		return connection == null;
	}
	
	public OutputUpdates read() {
		try {
			InputStream inputStream = getInputStream();
			
			if (inputStream == null) {
				return null;
			} else {
				return OutputUpdates.parseDelimitedFrom(inputStream);
			}
		} catch (IOException e) {
			close();
			throw new OIException(e);
		}
	}

	public boolean write(InputUpdates updates) {
		try {
			OutputStream outputStream = getOutputStream();
			
			if (outputStream == null) {
				return false;
			} else {
				updates.writeDelimitedTo(outputStream);
				return true;
			}
		} catch (IOException e) {
			close();
			throw new OIException(e);
		}
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
