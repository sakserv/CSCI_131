package com.shanekumpf.lab6;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import csci130.KeyboardReaderNG;

public class FileCreator {
	
	public static void main(String[] args) {
		
		System.out.print("Enter the absolute path to the file to be created: ");
		String filePath = KeyboardReaderNG.readLine();
		
		PrintStream outFile = null;
		try {
			outFile = new PrintStream(new BufferedOutputStream(
					new FileOutputStream(filePath)));
			String input = "";
			while(!input.equalsIgnoreCase("quit")) {
				if(!input.equalsIgnoreCase("")) {
					outFile.println(input);
				}
				input = KeyboardReaderNG.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Could not open file for writing: " + filePath);
			e.printStackTrace();
		}
		finally {
			try {
				outFile.close();
			} catch (Exception e) {}
		}
		
		
		
		DataInputStream inFile = null;
		try {
			inFile = new DataInputStream(new BufferedInputStream(
					new FileInputStream(filePath)));
			String curLine;
			while((curLine = inFile.readLine()) != null) {
				System.out.println(curLine);
			}
		}
		catch (IOException e) {
			System.out.println("Could not open file for writing: " + filePath);
			e.printStackTrace();
		}
		finally {
			try {
				inFile.close();
			} catch (Exception e) {}
		}
	}

}
