package com.bhrobotics.morcontrol.oi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

import com.bhrobotics.morcontrol.util.io.ByteConnection;

public class CRioOIServer implements OIServer {
	private ServerSocketConnection socket;
	private int port;
	
	public CRioOIServer(int port) {
		this.port = port;
		
		try {
			String url = "socket://:" + port;
			socket = (ServerSocketConnection) Connector.open(url);
		} catch (IOException e) {
			throw new OIException(e);
		}
	}
	
	public int getPort() {
		return port;
	}
	
	public ByteConnection accept() {
		try {
			StreamConnection streamConnection = socket.acceptAndOpen();
			InputStream inputStream = streamConnection.openInputStream();
			OutputStream outputStream = streamConnection.openOutputStream();
			return new ByteConnection(inputStream, outputStream);
		} catch (IOException e) {
			throw new OIException(e);
		}
	}
}
