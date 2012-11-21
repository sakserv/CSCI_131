package com.shanekumpf.lab13;

public class Train extends Thread {

	Tunnel tunnel;
	long timeInTunnel;
	long returnTimeToTunnel;
	int trainNumber;
	boolean canRun = true;
	
	public Train(long timeInTunnel, long returnTimeToTunnel) {
		this.timeInTunnel = timeInTunnel;
		this.returnTimeToTunnel = returnTimeToTunnel;
		tunnel = Tunnel.getInstance();
		trainNumber++;
	}
	
	public void run() {
		while(canRun) {
			synchronized(tunnel) {
				System.out.println("Train: " + this.trainNumber + " entering the tunnel");
				tunnel.enter(Integer.valueOf(Long.toString(returnTimeToTunnel)), timeInTunnel);
				System.out.println("Train: " + this.trainNumber + " leaving the tunnel");
				tunnel.leave(Integer.valueOf(Long.toString(returnTimeToTunnel)));
				tunnel.notifyAll();
			}
		}
		
		if(!canRun) {
			return;
		}
	}
	
	public void halt() {
		synchronized(tunnel) {
			canRun = false;
			tunnel.notifyAll();
		}
	}
	
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}
	
}
