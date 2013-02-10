package com.bhrobotics.morcontrol;

import java.util.Enumeration;
import java.util.Vector;

import org.apache.thrift.TProcessor;

public interface OIServer {

	public abstract void start();

	public abstract void stop();

	public abstract boolean isRunning();

	public abstract void addObserver(OIServerObserver observer);

	public abstract void removeObserver(OIServerObserver observer);

	public abstract Enumeration getObservers();

}