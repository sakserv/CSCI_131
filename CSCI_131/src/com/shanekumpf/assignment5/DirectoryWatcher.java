package com.shanekumpf.assignment5;

import java.io.File;
import java.util.ArrayList;

public class DirectoryWatcher extends Thread {
	
	private final Object SEMAPHORE = new Object();
	
	/**
	 * list of the files and directories previously seen
	 */
	ArrayList<File> prevFiles;
	
	/**
	 * list of the files and directories in the current directory
	 */
	ArrayList<File> curFiles = new ArrayList<File>();
	
	/**
	 * The current FileBrowser for callback
	 */
	FileBrowser fb;
	
	/**
	 * Thread will run when true.
	 */
	boolean canRun = true;
	
	/**
	 * The previous directory the user was in.
	 */
	File prevDir;
	
	/**
	 * Provide a reference to the FileBrowser that started this thread for callback purposes.
	 * @param fb	FileBrowser that started this Thread.
	 */
	public void setFileBrowser(FileBrowser fb) {
		this.fb = fb;
	}
	
	/**
	 * Stop the thread
	 */
	public void halt() {
		synchronized(SEMAPHORE) {
			canRun = false;
		}
	}
	
	/**
	 * If the user is still in the same directory as prevDir, compare prevFiles
	 * to the current files in the directory, if they don't match redisplay the menu
	 * with the updated contents. Check the directory about once per second.
	 */
	public void run() {
		while(canRun) {
			synchronized(SEMAPHORE) {
				if(prevFiles == null || !prevDir.equals(fb.getCurrentDirectory())) {
					prevFiles = new ArrayList<File>();
					prevFiles.addAll(fb.getDirectories());
					prevFiles.addAll(fb.getFiles(""));
					prevDir = fb.getCurrentDirectory();
				} else {
					curFiles.clear();
					curFiles.addAll(fb.getDirectories());
					curFiles.addAll(fb.getFiles(""));
					
					if((!prevFiles.containsAll(curFiles) || !curFiles.containsAll(prevFiles))) {
						System.out.println();
						System.out.println(">>> Directory Contents Changed");
						fb.displayDirectories();
						fb.displayFiles();
						fb.displayMenu();
						prevFiles = new ArrayList<File>(curFiles);
						prevDir = fb.getCurrentDirectory();
					}
				}
				try {
					SEMAPHORE.wait(1000);
				} catch (InterruptedException ie) {}
			}
		
		}

	}
}
