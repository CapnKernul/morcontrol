package com.bhrobotics.morcontrol.oi;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.util.RuntimeInterruptedException;
import com.bhrobotics.morcontrol.util.concurrent.BlockingQueue;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class QueuedMessageConnection implements MessageConnection {
	private ReentrantMutex mutex = new ReentrantMutex();
	private BlockingQueue readQueue = new BlockingQueue();
	private BlockingQueue writeQueue = new BlockingQueue();
	private Vector blockingThreads = new Vector();
	private boolean closed = false;
	
	public void close() {
		mutex.lock();
		closed = true;
		interruptBlockingThreads();
		mutex.unlock();
	}
	
	public void reopen() {
		mutex.lock();
		closed = false;
		mutex.unlock();
	}

	public boolean isClosed() {
		return closed;
	}
	
	public void putReadMessages(Message[] messages) {
		readQueue.put(messages);
	}

	public Message[] read() {
		addBlockingThread();
		
		try {
			return (Message[]) readQueue.take();
		} catch (RuntimeInterruptedException e) {
			throw new OIException(e);
		} finally {
			removeBlockingThread();
		}
	}
	
	public Message[] takeWrittenMessage() {
		addBlockingThread();
		
		try {
			return (Message[]) writeQueue.take();
		} catch (RuntimeInterruptedException e) {
			throw new OIException(e);
		} finally {
			removeBlockingThread();
		}
	}

	public void write(Message[] messages) {
		writeQueue.put(messages);
	}
	
	private void addBlockingThread() {
		mutex.lock();
		blockingThreads.addElement(Thread.currentThread());
		mutex.unlock();
	}
	
	private void removeBlockingThread() {
		mutex.lock();
		blockingThreads.removeElement(Thread.currentThread());
		mutex.unlock();
	}
	
	private void interruptBlockingThreads() {
		mutex.lock();
		Enumeration enumeration = blockingThreads.elements();
		while (enumeration.hasMoreElements()) {
			Thread thread = (Thread) enumeration.nextElement();
			thread.interrupt();
		}
		
		blockingThreads.removeAllElements();
		mutex.unlock();
	}
}
