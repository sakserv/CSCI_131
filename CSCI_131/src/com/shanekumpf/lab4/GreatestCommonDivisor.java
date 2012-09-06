package com.shanekumpf.lab4;

/**
 * Provides utilities for figuring out the Greatest Common Divisor between
 * two integers.
 * 
 * @author Shane Kumpf
 * @verion 0.1 09/05/12
 * @since 1.6
 *
 */
public class GreatestCommonDivisor {

	/**
	 * Computes the GCD of two integers (Recursive).
	 * @param x		first integer
	 * @param y		second integer
	 * @return int	the computed GCD
	 */
	static int gcdRecurse(int x, int y) {
		if (y <= x && x % y == 0) {
			return y;
		}
		if (x < y) {
			return gcdRecurse(y, x);
		}
		return gcdRecurse(y, (x % y));
	}
	
	/**
	 * Computes the GCD of two integers (Explicit).
	 * @param x		first integer
	 * @param y		second integer
	 * @return int	the computed GCD
	 */
	static int gcdExplicit(int x, int y) {
		if (y <= x && x % y == 0) {
			return y;
		}
		
		int divisor;
		if (y > x) {
			divisor = x;
		} else {
			divisor = y;
		}
		
		for (int i = divisor; i > 1; i--) {
			if (y % i == 0 && x % i == 0) {
				return i;
			}
		}
		return 1;
	}
	
	public static void main(String[] args) {
		System.out.println(gcdRecurse(35, 15));
		System.out.println(gcdExplicit(35, 15));

	}

}
