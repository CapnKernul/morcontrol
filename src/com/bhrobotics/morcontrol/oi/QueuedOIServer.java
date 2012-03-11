package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.concurrent.BlockingQueue;
import com.bhrobotics.morcontrol.util.io.ByteConnection;

public class QueuedOIServer implements OIServer {
	private BlockingQueue queue = new BlockingQueue();
	private int port;
	
	public QueuedOIServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void putConnection(ByteConnection connection) {
		queue.put(connection);
	}

	public ByteConnection accept() {
		return (ByteConnection) queue.take();
	}
}
