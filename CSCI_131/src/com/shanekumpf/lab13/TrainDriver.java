package com.shanekumpf.lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;

import csci130.KeyboardReaderNG;

public class TrainDriver {
	
	private static boolean validateTrainData(String trainData) {
		long timeInTunnel = 0;
		long returnTimeToTunnel = 0;
		
		// Validate we got two args.
		if(trainData.isEmpty() || trainData.trim().split("\\s+").length != 2) {
			System.out.println("Invalid data for the current train, removing train from the station. DATA: " + trainData);
			return false;
		}
		
		// Validate the values are not negative.
		timeInTunnel = Long.parseLong(trainData.trim().split("\\s+")[0]);
		returnTimeToTunnel = Long.parseLong(trainData.trim().split("\\s+")[1]);	
		if (timeInTunnel < 0 || returnTimeToTunnel < 0) {
			System.out.println("Invalid data for the current train, removing train from the station. DATA: " + trainData);
			return false;
		}
		return true;
	}
	
	public static final void main(String[] args) {
		System.out.print("Enter the full path to the train data file: ");
		String resp = KeyboardReaderNG.readLine();
		
		resp = "E:/IOTEST/Trains.dat";
		
		// Read the file, validate the data, and create the trainStation HashMap.
		BufferedReader reader = null;
		AbstractMap<Integer, String> trainStation = new HashMap<Integer, String>();
		
		try {
				reader = new BufferedReader(new FileReader(resp));
				String curLine = null;
				int trainNumber = 0;
				while((curLine = reader.readLine()) != null) {
					if (validateTrainData(curLine)) {
						trainStation.put(trainNumber++, curLine);
					}
				}
		} catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		} finally {
			try {
				reader.close();
			} catch(Exception e) {}
		}
		
		// Loop through all the trains. Data is already validated so get the data.
		// Create the train and set it's number. Start the train.
		long timeInTunnel = 0;
		long returnTimeToTunnel = 0;
		for(Integer trainNumber: trainStation.keySet()) {
			timeInTunnel = Long.parseLong(trainStation.get(trainNumber).trim().split("\\s+")[0]);
			returnTimeToTunnel = Long.parseLong(trainStation.get(trainNumber).trim().split("\\s+")[1]);
			Train train = new Train(returnTimeToTunnel, timeInTunnel);
			train.setTrainNumber(trainNumber);
			train.start();
		}
		
	}

}
