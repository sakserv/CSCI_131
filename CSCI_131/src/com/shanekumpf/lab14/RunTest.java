package com.shanekumpf.lab14;

public class RunTest implements Runnable {

	public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {}
		System.out.println("THREAD DONE");
	}
}
