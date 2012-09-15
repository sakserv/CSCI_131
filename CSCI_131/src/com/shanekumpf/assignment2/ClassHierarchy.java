package com.shanekumpf.assignment2;

import java.util.ArrayList;
import csci130.KeyboardReader;

/**
 * Provides utilities for printing out a class heirarchy in forward and reverse
 * order.
 * 
 * @author Shane Kumpf
 * @version 0.1 09/05/12
 * @since 1.6
 *
 */
public class ClassHierarchy {

	/**
	 * Prints out the class hierarchy of the provided class instance, starting
	 * at the supplied class. (Recurse)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyFromUserRecurse(Class<?> cls) {
		if (cls.getName() != "java.lang.Object") {
			System.out.println(cls.getName());
			printClassHeirarchyFromUserRecurse(cls.getSuperclass());
		} else {
			System.out.println("java.lang.Object");
		}
	}
	
	/**
	 * Prints out the class hierarchy of the provided class instance, starting
	 * at the supplied class. (Explicit)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyFromUserExplicit(Class<?> cls) {
		System.out.println(cls.getName());
		while (cls.getSuperclass() != null) {
			System.out.println(cls.getSuperclass().getName());
			cls = cls.getSuperclass();
		}
	}
	
	/**
	 * Prints out the class hierarchy of the provided class instance, starting 
	 * at java.lang.Object. (Recurse)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyFromObjectRecurse(Class<?> cls) {
		if (cls.getName() != "java.lang.Object") {
			printClassHeirarchyFromObjectRecurse(cls.getSuperclass());
		} 
		System.out.println(cls.getName());
	}
	
	
	/**
	 * Prints out the class hierarchy of the provided class instance, starting
	 * at java.lang.Object. (Explicit)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyFromObjectExplicit(Class<?> cls) {
		ArrayList<String> clsHeir = new ArrayList<String>();
		while (cls.getName() != "java.lang.Object") {
			clsHeir.add(cls.getName());
			cls = cls.getSuperclass();
		}
		clsHeir.add("java.lang.Object");
		for (int i = clsHeir.size() - 1; i >= 0; i--) {
			System.out.println(clsHeir.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		System.out.print("Enter a full class name to be walked: ");
		String resp = KeyboardReader.readLine();
		try {
			Class<?> cls = Class.forName(resp);
			
			System.out.println("\nFrom User Recurse: ");
			printClassHeirarchyFromUserRecurse(cls);
			
			System.out.println("\nFrom User Explicit: ");
			printClassHeirarchyFromUserExplicit(cls);
			
			System.out.println("\nFrom java.lang.Object Recurse: ");
			printClassHeirarchyFromObjectRecurse(cls);
			
			System.out.println("\nFrom java.lang.Object Explicit: ");
			printClassHeirarchyFromObjectExplicit(cls);
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: Invalid class specified: " + resp);
		}
	}

}
