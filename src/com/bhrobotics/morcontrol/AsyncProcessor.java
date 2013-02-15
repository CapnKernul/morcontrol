package com.bhrobotics.morcontrol;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;

public class AsyncProcessor {

    private Thread processor;

    public AsyncProcessor(TProcessor process, TProtocol protocol) {
	class AsyncProcess implements Runnable {
	    private TProcessor processor;
	    private TProtocol protocol;

	    public AsyncProcess(TProcessor processor, TProtocol protocol) {
		this.processor = processor;
		this.protocol = protocol;
	    }

	    public void run() {
		while (true) {
		    try {
			processor.process(protocol, protocol);
		    } catch (TException e) {
			break;
		    }
		}
	    }
	}
	this.processor = new Thread(new AsyncProcess(process, protocol));
    }

    public void start() {
	processor.start();
    }

    public void kill() {
	processor.interrupt();
    }

    public boolean isRunning() {
	return processor.isAlive();
    }
}
