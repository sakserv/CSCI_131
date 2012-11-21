package com.shanekumpf.lab13;

public class Tunnel {

	private static Tunnel instance = new Tunnel();
	
	private Tunnel() {}
	
	public static Tunnel getInstance() {
		return instance;
	}
	
	public void enter(int returnTimeToTunnel, long timeInTunnel) {
		try {
			Thread.sleep(timeInTunnel * 1000);
		} catch(InterruptedException ie) {}
	}
	
	public void leave(int returnTimeToTunnel) {
		try {
			instance.wait(returnTimeToTunnel * 1000);
		} catch(InterruptedException ie) {}
	}
}
