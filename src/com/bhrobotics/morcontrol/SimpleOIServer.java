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

import com.bhrobotics.morcontrol.io.DeviceTransport;
import com.bhrobotics.morcontrol.io.MorTransport;
import com.bhrobotics.morcontrol.io.Service;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class SimpleOIServer implements OIServer {
	private ReentrantLock lock = new ReentrantLock();
	private Vector observers = new Vector();
	private ServerSocketConnection socket;
	private Robot robot;

	public SimpleOIServer(Robot robot) throws IOException {
		this(robot, 1515);
	}

	public SimpleOIServer(Robot robot, int port) throws IOException {
		setRobot(robot);
		socket = (ServerSocketConnection) Connector.open("socket://:" + port);
	}

	public TProcessor getProcessor() {
		return new DeviceTransport.Processor(new Service(robot));
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
			thread = getServerThread();
			thread.start();
		}

		lock.unlock();
	}

	private BlockingServerThread getServerThread() {
		return new BlockingServerThread(this);
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

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	public boolean robotSet() {
		return robot == null;
	}

}
