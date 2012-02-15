package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.oi.hashes.InputHashMap;
import com.bhrobotics.morcontrol.oi.hashes.OutputHashMap;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class OperatorInterface {
	private ReentrantMutex mutex = new ReentrantMutex();
	private OIServer server;
	private MessageConnection connection;
	private ConnectionThread connectionThread = new ConnectionThread();
	private ReadingThread readingThread = new ReadingThread();
	private InputHashMap inputHash;
	private OutputHashMap outputHash;
	
	public OperatorInterface(OIServer server, InputHashMap inputHash, OutputHashMap outputHash) {
		this.server = server;
		this.inputHash = inputHash;
		this.outputHash = outputHash;
	}
	
	public void start() {
		connectionThread.start();
		readingThread.start();
	}

	public MessageConnection getConnection() {
		mutex.lock();
		
		MessageConnection result;
		if (isConnected()) {
			result = connection;
		} else {
			connection = null;
			result = null;
		}
		
		mutex.unlock();
		return result;
	}
	
	public boolean isConnected() {
		mutex.lock();
		boolean result = connection != null && !connection.isClosed();
		mutex.unlock();
		return result;
	}
	
	public void updateConnection() {
		mutex.lock();
		
		while (!isConnected()) {
			if (connection != null) {
				inputHash.clear();
				connection = null;
			}
			
			mutex.unlock();
			MessageConnection acceptedConnection = server.accept();
			mutex.lock();

			connection = acceptedConnection;
		}

		mutex.unlock();
	}

	public void forceUpdate() {
		mutex.lock();
		if (isConnected()) {
			try {
				MessageConnection conn = connection;
				mutex.unlock();
				
				System.out.println("Reading...");
				Message[] messages = conn.read();
				if (messages != null) {
					inputHash.update(messages);
					System.out.println("Updated with " + messages[0]);
				}
			} catch (OIException e) {
				System.out.println("[OI] An error has occured while reading: " + e.getMessage());
			}
		} else {
			mutex.unlock();
		}
	}
	
	private class ConnectionThread extends Thread {
		public void run() {
			while (true) {
				updateConnection();
			}
		}
	}

	private class ReadingThread extends Thread {
		public void run() {
			while (true) {
				forceUpdate();
			}
		}
	}
}
