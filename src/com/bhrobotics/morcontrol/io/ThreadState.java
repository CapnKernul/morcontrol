package com.bhrobotics.morcontrol.io;

public class ThreadState {
<<<<<<< HEAD
	public static final ThreadState CONNECTING = new ThreadState();
	public static final ThreadState PAUSED = new ThreadState();
	public static final ThreadState RUNNING = new ThreadState();
	public static final ThreadState DEAD = new ThreadState();

	private ThreadState() {
	}
=======
    public static final ThreadState RUNNING = new ThreadState();
    public static final ThreadState DEAD = new ThreadState();
    public static final ThreadState CONNECTING = new ThreadState();
    public static final ThreadState READY = new ThreadState();
    
    private ThreadState(){}
>>>>>>> 9ef8647b57bac198da7b2631bb34a64765ebb612
}
