package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncOIConnector {

	private ServerSocketConnection socket;
	private StreamConnection connection = null;
	private ReentrantLock lock = new ReentrantLock();
	private Thread thread = null;
	
	public AsyncOIConnector(ServerSocketConnection socket) {
		this.socket = socket;
	}
	
	public void establishConnection() {
		thread = new Thread(new Runnable() {
			public void run() {
				try {
					StreamConnection channel = socket.acceptAndOpen();
					connection = channel;
				} catch (IOException e) {
					Logger.defaultLogger.error("Connector formed bad connection");
				} 
			}			
		});
		thread.start();
	}
	
	public boolean isConnected() {
		return connection != null;
	}
	
	public TIOStreamTransport getConnection() {
		if(isConnected()) {
			try {
				InputStream in = connection.openInputStream();
				OutputStream out = connection.openOutputStream();
				TIOStreamTransport transport = new TIOStreamTransport(in, out); 
				return transport;
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}
}
