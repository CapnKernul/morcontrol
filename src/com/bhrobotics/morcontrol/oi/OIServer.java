package com.bhrobotics.morcontrol.oi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.bhrobotics.morcontrol.util.io.ConcurrentByteConnection;

public class OIServer {
	private int port;
	
	public OIServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}

	public MessageConnection accept() {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ConcurrentByteConnection connection = new ConcurrentByteConnection(inputStream, outputStream);
		return new ConcurrentMessageConnection(connection);
	}
}
