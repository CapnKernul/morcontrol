package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncThread extends Thread {

	private TProcessor processor;
	private ServerSocketConnection socket;
	private AsyncOIServer server;
	private ThreadTag tag;

	public AsyncThread(AsyncOIServer server, TProcessor processor, ServerSocketConnection socket, ThreadTag id) {
		this.processor = processor;
		this.socket = socket;
		this.server = server;
		tag = id;
	}

	public void run() {
		while (true) {
			try {
				TProtocol protocol = waitForConnection();
				server.threadConnected(getTag());
				
				// wait for all threads to be connected

				while (!server.allConnected()) {
					//Thread.yield();
				}
				
				Logger.defaultLogger.debug("Made it here");
				
				processLoop(protocol);
			} catch (IOException e) {
				Logger.defaultLogger.error("Interrupt while waiting for connection");
				server.threadDisconnected(getTag());
			} catch (TException e) {
				server.threadDisconnected(getTag());
			}
		}
	}

	public TProtocol waitForConnection() throws IOException {
		SocketConnection connection = (SocketConnection) socket.acceptAndOpen();
		InputStream in = connection.openInputStream();
		OutputStream out = connection.openOutputStream();
		TIOStreamTransport transport = new TIOStreamTransport(in, out);
		return new TBinaryProtocol(transport);
	}

	public void processLoop(TProtocol protocol) throws TException {
		while (true) {
			processor.process(protocol, protocol);
		}
	}

	public ThreadTag getTag() {
		return tag;
	}
}