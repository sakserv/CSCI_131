package com.shanekumpf.lab4;

public class ExtendedASCIIChart {

	private final int lastChar = 255;
	
	void printChartRecurse(int start) {
		printChartRecurse(start, start);
	}
	
	void printChartRecurse(int start, int end) {
		if (start - end == 10) {
			System.out.println();
			end += 10;
		}
		if (start <= lastChar) {
			System.out.print((char)start + "\t");
			printChartRecurse(start + 1, end);
		}
	}
	
	void printChartExplicit(int start) {
		for (int i = start; i <= lastChar; i++) {
			if (i % start == 10) {
				System.out.println();
				start = i;
			}
			System.out.print((char)i + "\t");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtendedASCIIChart eac = new ExtendedASCIIChart();
		System.out.println("Extended ASCII Chart Recursive:");
		eac.printChartRecurse(40);
		System.out.println("\n\nExtended ASCII Chart Explicit:");
		eac.printChartExplicit(40);

	}

}
