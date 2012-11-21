package com.shanekumpf.lab14;

import java.util.ConcurrentModificationException;

public class Worker extends Thread {
	
	private Object SEMAPHORE;
	Pool pool;
	Runnable work;
	boolean canRun = true;
	
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
			if(this.isAlive()) {
				throw new ConcurrentModificationException(this.getName() + " is already executing a Runnable");
			}
			this.work = work;
		}
	}
	
	public void halt() {
		synchronized(SEMAPHORE) {
			canRun = false;
		}
	}
	
	public void run() {
		while(canRun) {
			try {
				synchronized(SEMAPHORE) {
					work.run();
				}
			} finally {
				pool.workerHasFinished(this);
			}
		}
	}
}
