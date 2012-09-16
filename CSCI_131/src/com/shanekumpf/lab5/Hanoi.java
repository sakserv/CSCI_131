package com.shanekumpf.lab5;


import csci130.*;

public class Hanoi {

    Move[] moves;
    int numDisks;
    int moveCounter;

    public Hanoi(int numDisks) {
        this.numDisks = numDisks;
        this.moves = new Move[(int)Math.pow(2, numDisks + 1) - 1];
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
        if (numDisks == 0) {
                moves[moveCounter] = new Move(sourcePegNum,destPegNum);
                moveCounter += 1;
                return;
        }

        recurse(numDisks - 1, sourcePegNum, tempPegNum, destPegNum);
        moves[moveCounter] = new Move(sourcePegNum,destPegNum);
        moveCounter += 1;
        recurse(numDisks - 1, tempPegNum, destPegNum, sourcePegNum);
    }

    public static void main(String[] args) {
        System.out.print("\nEnter the total number of disks to move: ");
        int numDisks = KeyboardReader.readInt();

        System.out.print("\nEnter the start peg number: ");
        int sourcePegNum = KeyboardReader.readInt();

        System.out.print("\nEnter the destination peg number: ");
        int destPegNum = KeyboardReader.readInt();

        System.out.print("\nEnter the temp peg number: ");
        int tempPegNum = KeyboardReader.readInt();

        Hanoi hanoi = new Hanoi(numDisks);
        hanoi.solve(sourcePegNum, destPegNum, tempPegNum);
        hanoi.printMoves();
    }
}

