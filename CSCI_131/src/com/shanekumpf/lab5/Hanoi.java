package com.shanekumpf.lab5;

public class Hanoi {

	Move[] moves;
	int numDisks;
	int moveCounter;
	
	public Hanoi(int numDisks) {
		this.numDisks = numDisks;
		this.moves = new Move[(int)Math.pow(2, numDisks) - 1];
	}
	
	//public Move[] solve(int sourcePegNum, int destPegNum, int tempPegNum) {
		
	//}
	
}
