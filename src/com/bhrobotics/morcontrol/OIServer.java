package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransportException;

import com.bhrobotics.morcontrol.io.MorTransport;
import com.bhrobotics.morcontrol.io.Service;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class OIServer {
	private ReentrantLock lock = new ReentrantLock();
	private Vector observers = new Vector();
	private ServerSocketConnection socket;
	private Thread thread;
	
	public OIServer() throws IOException {
		this(1515);
	}
	
	public OIServer(int port) throws IOException {
		socket = (ServerSocketConnection) Connector.open("socket://:" + port);
	}
	
	public TProcessor getProcessor() {
		return new MorTransport.Processor(new Service());
	}
	
	public TProtocol acceptProtocol() throws IOException {
		StreamConnection streamConnection = socket.acceptAndOpen();
		InputStream inputStream = streamConnection.openInputStream();
		OutputStream outputStream = streamConnection.openOutputStream();
		TIOStreamTransport transport = new TIOStreamTransport(inputStream, outputStream);
		return new TBinaryProtocol(transport);
	}
	
	public void start() {
		lock.lock();
		
		if (!isRunning()) {
			thread = new ServerThread(this);
			thread.start();
		}
		
		lock.unlock();
	}

	public void stop() {
		lock.lock();
		
		if (isRunning()) {
			thread.interrupt();
			thread = null;
		}
		
		lock.unlock();
	}
	
	public boolean isRunning() {
		return thread != null;
	}
	
	public void addObserver(OIServerObserver observer) {
		observers.addElement(observer);
	}
	
	public void removeObserver(OIServerObserver observer) {
		observers.removeElement(observer);
	}
	
	public Vector getObservers() {
		return observers;
	}
	
	private static class ServerThread extends Thread {
		private final Logger logger = Logger.defaultLogger;
		private OIServer server;
		
		public ServerThread(OIServer server) {
			this.server = server;
		}
		
		public void run() {
			logger.info("Starting server.");
			TProcessor processor = server.getProcessor();

			try {
				while (true) {
					try {
						TProtocol protocol = server.acceptProtocol();
						oiConnected();
						processLoop(processor, protocol);
    	    		} catch (TTransportException e) {
    	    			oiDisconnected();
	    			} catch (TException e) {
	    				e.printStackTrace();
					}
				}
			} catch (InterruptedIOException e) {
				logger.info("Stopping server.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void oiConnected() {
			Enumeration e = server.getObservers().elements();
			while (e.hasMoreElements()) {
				OIServerObserver observer = (OIServerObserver) e.nextElement();
				observer.oiConnected();
			}
		}
		
		private void processLoop(TProcessor processor, TProtocol protocol) throws TException {
			while (true) {
				processor.process(protocol, protocol);
			}
		}
		
		private void oiDisconnected() {
			Enumeration e = server.getObservers().elements();
			while (e.hasMoreElements()) {
				OIServerObserver observer = (OIServerObserver) e.nextElement();
				observer.oiDisconnected();
			}
		}
	}
}
