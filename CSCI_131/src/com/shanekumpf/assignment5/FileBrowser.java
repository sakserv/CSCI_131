package com.shanekumpf.assignment5;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import csci130.KeyboardReader;

/**
 * This class provides a basic file browser. It displays directories and files; allows the user to change drives and directories; and allows the user to specify a basic file mask.
 * 
 * @author Shane Kumpf
 * @version 0.1 11/22/2012
 * @since 1.6
 *
 */
public class FileBrowser {

	/**
	 * File representing the current directory. Used by all the methods that access the file system.
	 */
	File currentDirectory = new File("/");
	
	/**
	 *  String representing the current file extension mask. Should have the form .wxyz where wxyz are any letters.
	 *  .* will be used to indicate that any file will work.
	 */
	String currentMask = "";
	
	/**
	 * Returns a sorted Collection of roots.
	 * @return A sorted collection.
	 */
	public final Collection<File> getRoots() {
		return new ArrayList<File>(Arrays.asList(File.listRoots()));
	}
	
	/**
	 * Returns a sorted Collection of files in the current directory.
	 * @return A sorted Collection.
	 */
	public final Collection<File> getFiles() {
		return getFiles("");
	}
	
	/**
	 * Returns a sorted Collection of files in the current directory which end with the passed in type mask. If no files match the type mask, then the collection will be empty.
	 * @param fileTypeMask A String containing the file type mask to apply to the list.
	 * @return A sorted Collection.
	 */
	public final Collection<File> getFiles(String fileTypeMask) {
		ArrayList<File> allFiles = new ArrayList<File>();
		
		currentMask = fileTypeMask;
		File[] fileList = currentDirectory.listFiles(new FilterResults());
		
		for (int i = 0; i < fileList.length; i++) {
			if(fileList[i].isFile()) {
				allFiles.add(fileList[i]);
			}
		}
		return allFiles;
	}
	
	/**
	 * Returns a sorted Collection containing subdirectories of the current directory.
	 * @return A sorted Collection.
	 */
	public final Collection<File> getDirectories() {
		ArrayList<File> allFiles = new ArrayList<File>();
		
		File[] fileList = currentDirectory.listFiles();
		
		for (int i = 0; i < fileList.length; i++) {
			if(fileList[i].isDirectory()) {
				allFiles.add(fileList[i]);
			}
		}
		return allFiles;
	}
	
	/**
	 * Attempts to change to a subdirectory of the current directory. If the passed in directory name does not exist, then the current directory stays the same. Otherwise the new directory becomes the current directory.
	 * @param newDir A String containing the name of the next intended directory.
	 * @return Returns the new current directory.
	 */
	public final File changeDirectoryDown(String newDir) {
		File newDirectory = new File(currentDirectory.getAbsolutePath() + "/" + newDir);
		if(newDirectory.exists()) {
			currentDirectory = newDirectory;
		}
		return currentDirectory;
	}
	
	/**
	 * Attempts to change the current directory to the current directory's parent. If the current directory does not have a parent, then the current directory remains unchanged.
	 * @return Returns the new current directory.
	 */
	public final File changeDirectoryUp() {
		if (currentDirectory.getParent() != null) {
			currentDirectory = new File(currentDirectory.getParent());
		}
		displayFullMenu();
		return currentDirectory;
	}
	
	/**
	 * Attempts to change the drive to the passed in drive name. If the passed in value does not match a drive name, then nothing will happen. Otherwise, the currentDrirectory will be set to the new drive root.
	 * @param newDriveName drive to switch to
	 * @return Indicates whether or not the drive was changed.
	 */
	public final boolean changeDrive(String newDriveName) {
		File newDrive = new File(newDriveName); 
		if(newDrive.exists()) {
			currentDirectory = newDrive;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the current directory.
	 * @return The currently active directory.
	 */
	public final File getCurrentDirectory() {
		return currentDirectory;
	}
	
	/**
	 * Displays the user menu
	 */
	void displayMenu() {
		char[] splats = new char[60];
		Arrays.fill(splats, '*');
		String splatsString = new String(splats);
		System.out.println(splatsString);
		System.out.println("1) Change The File Mask");
		System.out.println("2) Change To Subdirectory");
		System.out.println("3) Change to Parent Directory");
		System.out.println("4) Change Drive");
		System.out.println("5) Exit");
		System.out.print("Enter selection: ");
	}
	
	/**
	 * Displays a list of the directories in the current directory.
	 */
	final void displayDirectories() {
		for (File file : getDirectories()) {
			System.out.printf("%-30s %5s%n", file.getName(), "<DIR>");
		}
	}

	/**
	 * Displays a list of file in the current directory.
	 */
	final void displayFiles() {
		for (File file : getFiles(currentMask)) {
			System.out.println(file.getName());
		}
	}
	
	/**
	 * Displays the full menu which includes the directories, files, and user menu.
	 */
	final void displayFullMenu() {
		displayDirectories();
		displayFiles();
		displayMenu();
	}
	
	//Displays menu for setting the file mask
	private final void fileMaskMenu() {
		System.out.print("Type in the new file extension mask or press enter to clear it: ");
		currentMask = KeyboardReader.readLine();
		displayFullMenu();
	}
	
	//Displays menu for switching to a sub directory.
	private final void subDirMenu() {
		System.out.print("Enter the name of the new directory: ");
		currentDirectory = changeDirectoryDown(KeyboardReader.readLine());
		displayFullMenu();
	}
	
	//Displays the drive letter change menu
	private final void driveMenu() {
		System.out.println("Drives found: ");
		for(File drive: getRoots()) {
			System.out.println(drive.getPath());
		}
		System.out.print("Enter the drive to switch to: ");
		changeDrive(KeyboardReader.readLine());
		displayFullMenu();
	}
	
	// Main
	public static final void main(String[] args) {
		String resp = "";
		FileBrowser fb = new FileBrowser();
		DirectoryWatcher dw = new DirectoryWatcher();
		
		while(!resp.equals("5")) {
			
			if(resp.equals("")) {
				dw.setFileBrowser(fb);
				dw.setDaemon(true);
				dw.start();
				fb.displayFullMenu();
			}
			
			resp = KeyboardReader.readLine();
			if(!resp.equals("")) {
				switch(Integer.parseInt(resp)) {
					case 1:	fb.fileMaskMenu(); break;
					case 2:	fb.subDirMenu(); break;
					case 3:	fb.changeDirectoryUp(); break;
					case 4:	fb.driveMenu(); break;
				}
			}
		}
		dw.halt();
		System.out.println("Shutting down...");
	}
	
	
	/**
	 * Allows for setting a FilenameFilter for use with listing files
	 * 
	 * @author Shane Kumpf
	 * @version 0.1 11/22/2012
	 * @since 1.6
	 * 
	 */
	private class FilterResults implements FilenameFilter {
		
		/**
		 * Determine if the current mask matches the file being processed
		 * 
		 * @param dir	File representing the dir being processed
		 * @param name	Name of the file.
		 * @return true if the file contains the current mask.
		 */
		@Override
		public boolean accept(File dir, String name) {
			if(name.contains(currentMask)) {
				return true;
			}
			return false;
		}
	}
}
