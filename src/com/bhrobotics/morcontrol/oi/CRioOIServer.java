package com.bhrobotics.morcontrol.oi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

import com.bhrobotics.morcontrol.util.io.ConcurrentByteConnection;

public class CRioOIServer extends OIServer {
	private ServerSocketConnection socket;
	
	public CRioOIServer(int port) {
		super(port);
		
		try {
			String url = "socket://:" + port;
			socket = (ServerSocketConnection) Connector.open(url);
		} catch (IOException e) {
			throw new OIException(e);
		}
	}
	
	public ConcurrentMessageConnection accept() {
		try {
			StreamConnection streamConnection = socket.acceptAndOpen();
			InputStream inputStream = streamConnection.openInputStream();
			OutputStream outputStream = streamConnection.openOutputStream();
			ConcurrentByteConnection connection = new ConcurrentByteConnection(inputStream, outputStream);
			return new ConcurrentMessageConnection(connection);
		} catch (IOException e) {
			throw new OIException(e);
		}
	}
}
