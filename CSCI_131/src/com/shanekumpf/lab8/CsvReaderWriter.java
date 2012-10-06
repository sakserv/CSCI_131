package com.shanekumpf.lab8;

import java.io.*;

/**
 * Reads a csv file, calculates row/col averages and writes a new csv file.
 * 
 * @author Shane Kumpf
 * @version 0.1 10/04/12
 * @since 1.6
 * 
 */
public class CsvReaderWriter {

	static String FILENAME;
	static String NEW_FILENAME;

	/**
	 * Parse a csv file and loads the data into the supplied spreadsheet array
	 * 
	 * @param spreadsheet
	 *            represents the cells of a spreadsheet
	 */
	public static void csvParse(String[][] spreadsheet) {
		FileReader fr = null;
		StreamTokenizer tokenizer = null;

		try {
			fr = new FileReader(FILENAME);
			tokenizer = new StreamTokenizer(new BufferedReader(fr));
			tokenizer.whitespaceChars(',', ',');
			tokenizer.eolIsSignificant(true);

			int token = 0;
			for (int i = 0; i < spreadsheet.length; i++) {
				for (int j = 0; j < spreadsheet[i].length; j++) {
					if ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
						switch (token) {
						case StreamTokenizer.TT_WORD:
							spreadsheet[i][j] = tokenizer.sval;
							break;
						case StreamTokenizer.TT_NUMBER:
							spreadsheet[i][j] = Double.toString(tokenizer.nval);
							break;
						default:
							spreadsheet[i][j] = tokenizer.sval;
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				fr.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Adds the total labels to the spreadsheet
	 * 
	 * @param spreadsheet
	 *            represents the cells of a spreadsheet
	 */
	public static void addTotalLabels(String[][] spreadsheet) {
		spreadsheet[0][11] = "Total";
		spreadsheet[11][0] = "Total";
	}

	/**
	 * Averages each row and adds a total column
	 * 
	 * @param spreadsheet
	 *            represents the cells of a spreadsheet
	 */
	public static void addRowTotals(String[][] spreadsheet) {
		for (int i = 1; i < spreadsheet.length - 1; i++) {
			double rowTotal = 0;
			for (int j = 1; j < spreadsheet[i].length - 1; j++) {
				rowTotal += Double.parseDouble(spreadsheet[i][j]);
			}
			spreadsheet[i][11] = Double.toString(Math.round(rowTotal
					/ spreadsheet[i].length - 1));
		}
	}

	/**
	 * Average each col and adds a total row
	 * 
	 * @param spreadsheet
	 *            represents the cells of a spreadsheet.
	 */
	public static void addColTotals(String[][] spreadsheet) {
		for (int i = 1; i < spreadsheet.length - 1; i++) {
			double rowTotal = 0;
			for (int j = 1; j < spreadsheet[i].length - 1; j++) {
				rowTotal += Double.parseDouble(spreadsheet[j][i]);
			}
			spreadsheet[11][i] = Double.toString(Math.round(rowTotal
					/ spreadsheet[i].length - 1));
		}
	}

	/**
	 * Write a new csv file containing the row/col averages
	 * 
	 * @param spreadsheet
	 *            represents the cells of a spreadsheet.
	 */
	public static void writeNewFile(String[][] spreadsheet) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(NEW_FILENAME)));
			for (int i = 0; i < spreadsheet.length; i++) {
				writer.println();
				for (int j = 0; j < spreadsheet[i].length; j++) {
					
					// don't add any fields that have null as the string val.
					if (spreadsheet[i][j] == null) {
						break;
					}
					
					// don't add a comma at the end of a line
					if (j == spreadsheet[i].length) {
						writer.print(spreadsheet[i][j]);
					} else {
						writer.print(spreadsheet[i][j] + ",");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				writer.close();
			} catch (Exception e) {}
		}
		
	}

	public static final void main(String[] args) {
		FILENAME = "E:/IOTEST/grades.csv";
		NEW_FILENAME = "E:/IOTEST/grades_new.csv";
		String[][] spreadsheet = new String[12][12];

		csvParse(spreadsheet);
		addTotalLabels(spreadsheet);
		addColTotals(spreadsheet);
		addRowTotals(spreadsheet);
		writeNewFile(spreadsheet);

	}

}
