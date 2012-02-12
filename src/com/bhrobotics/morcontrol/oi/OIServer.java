package com.bhrobotics.morcontrol.oi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.bhrobotics.morcontrol.util.Connection;

public class OIServer {
	private int port;
	
	public OIServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}

	public OIConnection accept() {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Connection connection = new Connection(inputStream, outputStream);
		return new OIConnection(connection);
	}
}
