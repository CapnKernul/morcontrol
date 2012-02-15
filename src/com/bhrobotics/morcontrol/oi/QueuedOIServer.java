package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.concurrent.BlockingQueue;

public class QueuedOIServer implements OIServer {
	private BlockingQueue queue = new BlockingQueue();
	private int port;
	
	public QueuedOIServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void putConnection(MessageConnection connection) {
		queue.put(connection);
	}

	public MessageConnection accept() {
		return (MessageConnection) queue.take();
	}
}
