package com.shanekumpf.lab14;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Pool extends Thread {

	private long workerThreadCount;
	private final Object SEMAPHORE;
	private ArrayList<Runnable> workWaiting;
	private boolean canRun = true;

	private ArrayList<Worker> workersNeedWork;
	private ArrayList<Worker> workersHaveWork;

	public Pool() {
		SEMAPHORE = new Object();
		workWaiting = new ArrayList<Runnable>();
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
			
			System.out.println("WAITING: " + workWaiting.size());
			System.out.println("NEED WORK: " + workersNeedWork.size());
			System.out.println("HAVE WORK: " + workersHaveWork.size());

			synchronized (SEMAPHORE) {
				int currentWorkerCount = workersNeedWork.size()	+ workersHaveWork.size();
				if (workersNeedWork.size() == 0	&& currentWorkerCount <= workerThreadCount) {
					workersNeedWork.add(new Worker());
					
				} else if(workersNeedWork.size() > 0) {
					// TODO: This area not working as expected.
					for(int i = 0; i < workersNeedWork.size(); i++) {
						
						//System.out.println(workersNeedWork.get(0).toString());
						Worker worker = workersNeedWork.get(i);
						worker.setPool(this);
					
						try {
							worker.setWork(workWaiting.get(i));
						} catch(ConcurrentModificationException cme) {
							workersNeedWork.remove(worker);
							workersHaveWork.add(worker);
							continue;
						}
					
						worker.start();
						workersNeedWork.remove(worker);
						workersHaveWork.add(worker);
						workWaiting.remove(i);
					}
					
				} else {
					System.out.println("No workers available");
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {}
			}
		}
	}

}
