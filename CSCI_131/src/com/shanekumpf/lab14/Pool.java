package com.shanekumpf.lab14;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Queue;

public class Pool extends Thread {

	private long workerThreadCount;
	private final Object SEMAPHORE;
	private Queue<Runnable> workWaiting;
	private boolean canRun = true;

	private ArrayList<Worker> workersNeedWork;
	private ArrayList<Worker> workersHaveWork;

	public Pool() {
		SEMAPHORE = new Object();
		workWaiting = new LinkedList<Runnable>();
		workersNeedWork = new ArrayList<Worker>();
		workersHaveWork = new ArrayList<Worker>();
	}
	
	public long getWorkerThreadCount() {
		return workerThreadCount;
	}

	public void setWorkerThreadCount(long workerThreadCount) {
		this.workerThreadCount = workerThreadCount;
	}

	public void addWork(Runnable r) {
		synchronized (SEMAPHORE) {
			workWaiting.add(r);
			SEMAPHORE.notifyAll();
		}
	}

	public void workerHasFinished(Worker wt) {
		synchronized (SEMAPHORE) {
			workersHaveWork.remove(wt);
			workersNeedWork.add(wt);
		}
	}

	public void halt() {
		synchronized (SEMAPHORE) {
			canRun = false;
		}
	}

	public void run() {
		while (canRun) {
			if (workWaiting.isEmpty()) {
				try {
					Thread.sleep(100);
					continue;
				} catch (InterruptedException ie) {}
			}

			synchronized (SEMAPHORE) {			
				int currentWorkerCount = workersNeedWork.size()	+ workersHaveWork.size();
				if (workersNeedWork.size() == 0	&& currentWorkerCount < workerThreadCount) {
					workersNeedWork.add(new Worker());
				}
					
				if(workersNeedWork.size() > 0) {
					
					for(int i = 0; i < workersNeedWork.size(); i++) {
						
						Worker worker = workersNeedWork.get(i);
						worker.setPool(this);
					
						try {
							worker.setWork(workWaiting.peek());
							workersNeedWork.remove(i);
							workersHaveWork.add(worker);
							workWaiting.poll();
						} catch(ConcurrentModificationException cme) {
							continue;
						}
						
						if(!worker.isAlive()) {
							worker.start();
						}
					}
					
				} else {
					System.out.println("No workers available");
					try {
						Thread.sleep(500);
					} catch (InterruptedException ie) {}
				}
				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {}
		
		}
	}

}
