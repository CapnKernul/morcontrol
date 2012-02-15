package com.bhrobotics.morcontrol.oi;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.hashes.InputHashMap;
import com.bhrobotics.morcontrol.oi.hashes.OutputHashMap;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class OperatorInterface {
	private AutomaticMessageConnection connection;
	private InputHashMap inputHash;
	private OutputHashMap outputHash;
	
	public OperatorInterface() {
		this(new OIServerFactory().create());
	}

	public OperatorInterface(int port) {
		this(new OIServerFactory().create(port));
	}
	
	public OperatorInterface(OIServer server) {
		this(server, new InputHashMap(), new OutputHashMap());
	}
	
	public OperatorInterface(OIServer server, InputHashMap inputHash, OutputHashMap outputHash) {
		connection = new AutomaticMessageConnection(server);
		this.inputHash = inputHash;
		this.outputHash = outputHash;
		registerObservers();
	}
	
	private void registerObservers() {
		outputHash.setConnection(connection);
		connection.registerObserver(new AutomaticMessageConnectionObserver() {
			public void openedConnection() {
				outputHash.resend();
			}

			public void closedConnection() {
				inputHash.clear();
			}

			public void readMessages() {
			}

			public void wroteMessages() {
			}

			public void exceptionWhileReading(OIException e) {
				System.err.println("Error while reading from stream: " + e.getMessage());
			}

			public void exceptionWhileWriting(OIException e) {
				System.err.println("Error while writing to stream: " + e.getMessage());
			}
		});
	}
	
	public boolean isClosed() {
		return connection.isClosed();
	}
	
	public void requireConnection() {
		connection.requireConnection();
	}
	
	public void verifyConnection() {
		connection.verifyConnection();
	}
	
	public void update() {
		Message[] messages = connection.read();
		if (messages != null) {
			inputHash.update(messages);
		}
	}
}
