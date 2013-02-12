package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Enumeration;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;

import com.bhrobotics.morcontrol.util.logger.Logger;

public class BlockingServerThread extends Thread {
    private final Logger logger = Logger.defaultLogger;
    private OIServer oIServer;

    public BlockingServerThread(OIServer oIServer) {
	this.oIServer = oIServer;
    }

    public void run() {
	logger.info("Starting server.");
	TProcessor processor = oIServer.getProcessor();

	try {
	    while (true) {
		try {
		    TProtocol protocol = oIServer.acceptProtocol();
		    oiConnected();
		    processLoop(processor, protocol);
		} catch (TTransportException e) {
		    oiDisconnected();
		} catch (TException e) {
		    e.printStackTrace();
		}
	    }
	} catch (InterruptedIOException e) {
	    logger.info("Stopping server.");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void oiConnected() {
	Enumeration e = oIServer.getObservers();
	while (e.hasMoreElements()) {
	    OIServerObserver observer = (OIServerObserver) e.nextElement();
	    observer.oiConnected();
	}
    }

    private void processLoop(TProcessor processor, TProtocol protocol) throws TException {
	while (true) {
	    processor.process(protocol, protocol);
	}
    }

    private void oiDisconnected() {
	Enumeration e = oIServer.getObservers();
	while (e.hasMoreElements()) {
	    OIServerObserver observer = (OIServerObserver) e.nextElement();
	    observer.oiDisconnected();
	}
    }
}
