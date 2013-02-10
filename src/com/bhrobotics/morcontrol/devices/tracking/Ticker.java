package com.bhrobotics.morcontrol.devices.tracking;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.util.concurrent.ReentrantLock;

public class Ticker implements Tickable {
	private Vector tickables = new Vector();
	private boolean stopped = true;
	private ReentrantLock lock = new ReentrantLock();
	private Thread ticker = null;

	public Ticker() {
		this(0);
	}

	public Ticker(int cycle) {
		class TickerLoop implements Runnable {
			Ticker ticker;
			int cycle;

			public TickerLoop(Ticker ticker) {
				this.ticker = ticker;
			}

			public void run() {
				while (!Thread.interrupted()) {
					if (!stopped) {
						ticker.tick();
					}
					Thread.yield();
				}
			}
		}

		ticker = new Thread(new TickerLoop(this));
		ticker.start();
	}

	public void addTickable(Tickable tickable) {
		lock.lock();
		tickables.addElement(tickable);
		lock.unlock();
	}

	public void removeTickable(Tickable tickable) {
		lock.lock();
		tickables.remove(tickable);
		lock.unlock();
	}

	public void tick() {
		lock.lock();
		Enumeration e = tickables.elements();
		while (e.hasMoreElements()) {
			Tickable tickable = ((Tickable) e.nextElement());
			tickable.tick();
		}
		lock.unlock();
	}

	public void kill() {
		lock.lock();
		if (ticker.isAlive()) {
			ticker.interrupt();
		}
		lock.unlock();
		stop();
	}

	public void start() {
		stopped = false;
	}

	public void stop() {
		stopped = true;
	}

	public boolean isStopped() {
		return stopped;
	}
}