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

import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncThread extends Thread {

	private TProcessor processor;
	private ServerSocketConnection socket;
	private ThreadStateHolder holder = new ThreadStateHolder();

	public AsyncThread(TProcessor processor, ServerSocketConnection socket) {
		this.processor = processor;
		this.socket = socket;
	}

	public void run() {
		try {
			holder.setState(ThreadState.CONNECTING);
			TProtocol protocol = waitForConnection();
			holder.setState(ThreadState.READY);
			while (!Thread.interrupted()) {
				ThreadState state = holder.getState();
				if (state == ThreadState.DEAD) {
					Thread.currentThread().interrupt();
				} else if (state == ThreadState.RUNNING) {
					process(protocol);
				}
			}
		} catch (IOException e) {
			Logger.defaultLogger.error("Could not open connection on a port");
		} catch (TException e) {
			holder.setState(ThreadState.DEAD);
		}
	}

	public TProtocol waitForConnection() throws IOException {
		SocketConnection connection = (SocketConnection) socket.acceptAndOpen();
		InputStream in = connection.openInputStream();
		OutputStream out = connection.openOutputStream();
		TIOStreamTransport transport = new TIOStreamTransport(in, out);
		return new TBinaryProtocol(transport);
	}

	public void process(TProtocol protocol) throws TException {
		processor.process(protocol, protocol);
	}

	public ThreadStateHolder getThreadStateHolder() {
		return holder;
	}
}