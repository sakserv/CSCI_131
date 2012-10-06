package com.shanekumpf.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import csci130.KeyboardReaderNG;

/**
 * Utilities for building and saving cell automatons
 * 
 * @author Shane Kumpf
 * @version 0.1 10/4/12
 * @since 1.6
 * 
 */
public class CA {

	private boolean[][] grid;
	private int minNeighbours;
	private int maxNeighbours;
	private int currentGeneration;
	private int maxGenerations;
	private int modulus;
	private File saveFile;

	/**
	 * Check arguments and setup the intial grid based on provided probability.
	 * 
	 * @param gridSize
	 *            The size of the grid
	 * @param initialProbability
	 *            threshold used to determine if a cell is alive
	 * @param minNeighbours
	 *            number of neighbors needed for a cell to be alive
	 * @param maxNeighbours
	 *            max number of neighbors before a cell dies
	 * @param maxGenerations
	 *            how many iterations
	 * @param modulus
	 *            used to determine how often to render the grid
	 * @param fileName
	 *            output file path
	 */
	public CA(int gridSize, double initialProbability, int minNeighbours,
			int maxNeighbours, int maxGenerations, int modulus, String fileName) {
		if (gridSize < 5 || gridSize >= 50) {
			throw new IllegalArgumentException("gridSize must be between 5 "
					+ "and 50.");
		}
		if (initialProbability < 0 || initialProbability >= 1) {
			throw new IllegalArgumentException("initialProbability must be "
					+ "between 0 and 1 exclusive of 1");
		}
		if (minNeighbours <= 1 || minNeighbours >= 4) {
			throw new IllegalArgumentException("minNeighbours must be between "
					+ "1 and 4");
		}
		if (maxNeighbours <= 2 || maxNeighbours >= 8) {
			throw new IllegalArgumentException("maxNeighbours must be between "
					+ "2 and 8");
		}
		if (modulus > maxGenerations) {
			throw new IllegalArgumentException("modulus must be less than "
					+ "maxGenerations");
		}

		grid = new boolean[gridSize][gridSize];
		this.minNeighbours = minNeighbours;
		this.maxNeighbours = maxNeighbours;
		this.maxGenerations = maxGenerations;
		this.modulus = modulus;
		saveFile = new File(fileName);
		setupInitialState(initialProbability);
	}

	/**
	 * Check each cell to determine if it should be alive, increment the
	 * generation save if generation is divisible by the modulus.
	 * 
	 * @return true if more generations to process
	 */
	public boolean createNextGeneration() {
		boolean[][] grid = this.grid.clone();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (shouldBeAlive(i, j)) {
					grid[i][j] = true;
				} else {
					grid[i][j] = false;
				}
			}
		}
		this.grid = grid;
		currentGeneration++;
		if (currentGeneration % modulus == 0) {
			save();
		}
		if (currentGeneration <= maxGenerations) {
			return true;
		}
		return false;
	}

	/**
	 * Save the current grid to saveFile
	 */
	public void save() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(
					saveFile, true)));

			writer.println();
			writer.println("Current gen: " + currentGeneration);
			writer.println();

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j]) {
						writer.print('*');
					} else {
						writer.print(' ');
					}
				}
				writer.println();
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Mark cell alive if the supplied probability is greater than a randomly
	 * generated number.
	 * 
	 * @param initialProbability
	 */
	private void setupInitialState(double initialProbability) {
		Random randNum = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (initialProbability < randNum.nextDouble()) {
					grid[i][j] = false;
				} else {
					grid[i][j] = true;
				}
			}
		}
	}

	/**
	 * Determines if a particular cell should be alive in the next generation.
	 * 
	 * @param i
	 *            row in the grid
	 * @param j
	 *            col in the grid
	 * @return true if the cell should be alive, false otherwise.
	 */
	private boolean shouldBeAlive(int i, int j) {
		int foundAlive = 0;
		// Check cell above current
		if (i - 1 < 0) {
			if (grid[(i - 1) + grid.length][j]) {
				foundAlive++;
			}
		} else {
			if (grid[i - 1][j]) {
				foundAlive++;
			}
		}

		// Check cell below current
		if ((i + 1) > (grid.length - 1)) {
			if (grid[(i + 1) - grid.length][j]) {
				foundAlive++;
			}
		} else {
			if (grid[i + 1][j]) {
				foundAlive++;
			}
		}

		// Check cell to the left
		if (j - 1 < 0) {
			if (grid[i][(j - 1) + grid[i].length]) {
				foundAlive++;
			}
		} else {
			if (grid[i][j - 1]) {
				foundAlive++;
			}
		}

		// Check cell to the right
		if ((j + 1) > (grid[i].length - 1)) {
			if (grid[i][(j + 1) - grid[i].length]) {
				foundAlive++;
			}
		} else {
			if (grid[i][j + 1]) {
				foundAlive++;
			}
		}

		// foundAlive must be between max and min neighbours
		if (foundAlive >= minNeighbours && foundAlive <= maxNeighbours) {
			return true;
		} else {
			return false;
		}
	}

	public static final void main(String[] args) {
		System.out.print("Enter a grid size: ");
		int gridSize = KeyboardReaderNG.readInt();

		System.out.print("Enter the initial probability: ");
		double initialProbability = KeyboardReaderNG.readDouble();

		System.out.print("Enter the min neighbours allowed: ");
		int minNeighbours = KeyboardReaderNG.readInt();

		System.out.print("Enter the max neighbours allowed: ");
		int maxNeighbours = KeyboardReaderNG.readInt();

		System.out.print("Enter the number of generations: ");
		int maxGenerations = KeyboardReaderNG.readInt();

		System.out.print("Enter the save interval: ");
		int modulus = KeyboardReaderNG.readInt();

		System.out.print("Enter the filename where results will be saved: ");
		String fileName = KeyboardReaderNG.readLine();

		CA ca = new CA(gridSize, initialProbability, minNeighbours,
				maxNeighbours, maxGenerations, modulus, fileName);

		// For testing
		// CA ca = new CA(10, 0.60, 2, 3, 50, 1, "C:/testfile.txt");

		boolean nextGenAvailable;
		do {
			nextGenAvailable = ca.createNextGeneration();
		} while (nextGenAvailable != false);
	}

}
