package com.shanekumpf.assignment2;

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
	 * @param args
	 */
	static void printClassHeirarchyFromObjectRecurse(Class<?> cls) {
		if (cls.getName() != "java.lang.Object") {
			printClassHeirarchyFromObjectRecurse(cls.getSuperclass());
		} 
		System.out.println(cls.getName());
	}
	
	public static void main(String[] args) {
		printClassHeirarchyFromUserRecurse(javax.swing.JSplitPane.class);
		System.out.println();
		printClassHeirarchyFromUserExplicit(javax.swing.JSplitPane.class);
		System.out.println();
		printClassHeirarchyFromObjectRecurse(javax.swing.JSplitPane.class);
	}

}
