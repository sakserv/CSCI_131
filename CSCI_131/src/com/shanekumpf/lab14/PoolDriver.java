package com.shanekumpf.lab14;

public class PoolDriver {
	
	public static final void main(String[] args) {
		Pool pool = new Pool();
		pool.setWorkerThreadCount(2);
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
		pool.start();
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
		pool.addWork(new RunTest());
	}
}
