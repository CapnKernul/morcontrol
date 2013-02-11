package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncServerThread extends Thread {

	private ThreadState threadState = ThreadState.PAUSED;
	private TProcessor processor;
	private ServerSocketConnection socket;

	public AsyncServerThread(TProcessor processor, ServerSocketConnection socket) {
		this.processor = processor;
		this.socket = socket;
	}

	public void run() {
		try {
			threadState = ThreadState.CONNECTING;
			StreamConnection connection = (StreamConnection) socket.acceptAndOpen();
			InputStream in = connection.openInputStream();
			OutputStream out = connection.openOutputStream();
			TIOStreamTransport transport = new TIOStreamTransport(in, out);
			TBinaryProtocol protocol = new TBinaryProtocol(transport);
			threadState = ThreadState.PAUSED;

			while (!Thread.interrupted()) {
				try {
					if (threadState == ThreadState.RUNNING) {
						processor.process(protocol, protocol);
					} else if (threadState == ThreadState.DEAD) {
						Thread.currentThread().interrupt();
						kill();
					}
					Thread.yield();
				} catch (TException e) {
					Logger.defaultLogger.error(e.getMessage());
					kill();
				}
			}
		} catch (IOException e) {
			Logger.defaultLogger.error("ServerSocketConnection failed");
			kill();
		}
	}

	public ThreadState getThreadState() {
		return threadState;
	}

	public void setThreadState(ThreadState threadState) {
		this.threadState = threadState;
	}

	public void kill() {
		threadState = ThreadState.DEAD;
	}

	public void pause() {
		threadState = ThreadState.PAUSED;
	}

	public void unpause() {
		threadState = ThreadState.RUNNING;
	}
}