package com.shanekumpf.lab14;

import java.util.ConcurrentModificationException;

public class Worker extends Thread {
	
	private Object SEMAPHORE;
	Pool pool;
	Runnable work;
	boolean canRun = true;
	boolean workInProgress = false;
	
	public Worker() {
		SEMAPHORE = new Object();
	}
	
	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}
	
	public void setWork(Runnable work) {
		synchronized(SEMAPHORE) {
			if(workInProgress) {
				throw new ConcurrentModificationException(this.getName() + " is already executing a Runnable");
			}
			this.work = work;
			workInProgress = true;
		}
	}
	
	public void halt() {
		synchronized(SEMAPHORE) {
			canRun = false;
		}
	}
	
	public void run() {
		while(canRun) {
			synchronized(SEMAPHORE) {
				if (work != null) {
					Thread workThread = new Thread(work);
					workThread.start();
					while(workThread.getState().equals(State.NEW) || workThread.getState().equals(State.RUNNABLE) || workThread.getState().equals(State.BLOCKED)) {
						try {
							Thread.sleep(100);
						} catch(InterruptedException ie) {}
					}
					workInProgress = false;
					pool.workerHasFinished(this);
					work = null;
				}
			}
		}
	}
}
