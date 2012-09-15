package com.shanekumpf.lab5;


public class Move {

    int startPeg;
    int endPeg;

    public Move(int startPeg, int endPeg) {
        this.startPeg = startPeg;
        this.endPeg = endPeg;
    }

    public String toString() {
        return startPeg + " -> " + endPeg;
    }

    public boolean equals(Object o) {
        if (Move.class.isInstance(o)) {
            Move oM = (Move)o; // Got to be a better way to do this casting
            if (this.startPeg != oM.startPeg || this.endPeg != oM.endPeg) {
                return false;
            }
            return true;
        }
        return false;
    }
}

