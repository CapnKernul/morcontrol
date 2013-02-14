package com.bhrobotics.morcontrol;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;

public class AsyncProcessor {
	
	private Thread processor;
	
	public AsyncProcessor(final TProcessor processor, final TProtocol protocol) {
		this.processor = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						processor.process(protocol, protocol);
					} catch (TException e) {
						break;
					}
				}
			}
		});
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
