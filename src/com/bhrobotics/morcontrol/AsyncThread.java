package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import com.bhrobotics.morcontrol.io.ThreadState;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncThread extends Thread {

    private ThreadState threadState = ThreadState.RUNNING;
    private TProcessor processor;
    private boolean connected = false;
    private ServerSocketConnection socket;

    public AsyncThread(TProcessor processor, ServerSocketConnection socket) {
	this.processor = processor;
	this.socket = socket;
    }

    public void run() {
	try {
	    SocketConnection connection = (SocketConnection)socket.acceptAndOpen();
	    InputStream in = connection.openInputStream();
	    OutputStream out = connection.openOutputStream();
	    TIOStreamTransport transport = new TIOStreamTransport(in, out);
	    TBinaryProtocol protocol = new TBinaryProtocol(transport);
	    
	    while (!Thread.interrupted()) {
		if (threadState == ThreadState.RUNNING) {
		    processor.process(protocol, protocol);
		} else if (threadState == ThreadState.DEAD) {
		    Thread.currentThread().interrupt();
		}
		Thread.yield();
	    }
	} catch (TException e) {
	    Logger.defaultLogger.error(e.getMessage());
	    kill();
	} catch (IOException e) {
	    Logger.defaultLogger.error("ServerSocket failed");
	    kill();
	}
    }

    public ThreadState getThreadState() {
	return threadState;
    }

    public boolean isConnected() {
	return connected;
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