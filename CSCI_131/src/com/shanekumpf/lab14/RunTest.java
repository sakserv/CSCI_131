package com.shanekumpf.lab14;

public class RunTest implements Runnable {

	public void run() {
		try {
			for (long i = 0, j = 0; i < 1000000000; i++) {
				j++;
			}
			Thread.sleep(10000);
		} catch (InterruptedException ie) {}
		System.out.println("THREAD DONE");
	}
}
