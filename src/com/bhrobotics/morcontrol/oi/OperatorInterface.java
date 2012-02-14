package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.oi.hashes.InputHashMap;
import com.bhrobotics.morcontrol.oi.hashes.OutputHashMap;
import com.bhrobotics.morcontrol.oi.messages.Message;

public class OperatorInterface {
	private MessageConnection connection;
	private ConnectionThread connectionThread;
	private ReadingThread readingThread;
	private OIServer server;
	private InputHashMap inputHash;
	private OutputHashMap outputHash;
	
	public OperatorInterface(OIServer server, InputHashMap inputHash, OutputHashMap outputHash) {
		this.server = server;
		this.inputHash = inputHash;
		this.outputHash = outputHash;
		resume();
	}
	
	public void pause() {
		if (!isPaused()) {
			connectionThread.interrupt();
			readingThread.interrupt();
		}
	}
	
	public void resume() {
		if (isPaused()) {
			connectionThread = new ConnectionThread();
			connectionThread.start();

			readingThread = new ReadingThread();
			readingThread.start();
		}
	}

	public MessageConnection getConnection() {
		return connection;
	}
	
	public boolean isPaused() {
		return connectionThread == null || !connectionThread.isAlive();
	}
	
	public boolean isConnected() {
		return connection != null && !connection.isClosed();
	}
	
	private class ConnectionThread extends Thread {
		public void run() {
			while (true) {
				if (!isConnected()) {
					if (connection != null) {
						connection = null;
						inputHash.clear();
					}

					connection = server.accept();
				}
			}
		}
	}

	private class ReadingThread extends Thread {
		public void run() {
			while (true) {
				if (isConnected()) {
					try {
						Message[] messages = connection.read();
						if (messages != null) {
							inputHash.update(messages);
						}
					} catch (OIException e) {
						System.out.println("[OI] An error has occured while reading: " + e.getMessage());
					}
				}
			}
		}
	}
}
