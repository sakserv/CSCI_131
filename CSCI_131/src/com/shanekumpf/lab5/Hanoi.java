package com.shanekumpf.lab5;

public class Hanoi {

	Move[] moves;
	int numDisks;
	int moveCounter;
	
	public Hanoi(int numDisks) {
		this.numDisks = numDisks;
		this.moves = new Move[(int)Math.pow(2, numDisks) - 1];
	}
	
	public Move[] solve(int sourcePegNum, int destPegNum, int tempPegNum) {
		moveCounter = 0;
		recurse(numDisks, sourcePegNum, destPegNum, tempPegNum);
		return moves;
	}
	
	public void printMoves() {
		for (Move move: moves) {
			System.out.println(move.toString());
		}
	}
	
	private void recurse(int numDisks, int sourcePegNum, int destPegNum, 
			int tempPegNum) {
		// TODO: write the method...
	}
	
	
}
