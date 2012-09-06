package com.shanekumpf.lab4;

/**
 * Provides utilities for printing out a class heirarchy.
 * @author Shane Kumpf
 * @version 0.1 09/05/12
 * @since 1.6
 *
 */
public class ClassHierarchy {

	/**
	 * Prints out the class hierarchy of the provided class instance. (Explicit)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyRecurse(Class cls) {
		if (cls.getName() != "java.lang.Object") {
			System.out.println(cls.getName());
			printClassHeirarchyRecurse(cls.getSuperclass());
		} else {
			System.out.println("java.lang.Object");
		}
	}
	
	/**
	 * Prints out the class hierarchy of the provided class instance. (Explicit)
	 * 
	 * @param cls	class instance to walk
	 */
	static void printClassHeirarchyExplicit(Class cls) {
		System.out.println(cls.getName());
		while (cls.getSuperclass() != null) {
			System.out.println(cls.getSuperclass().getName());
			cls = cls.getSuperclass();
		}
	}
	
	public static void main(String[] args) {
		printClassHeirarchyRecurse(javax.swing.JSplitPane.class);
		System.out.println();
		printClassHeirarchyExplicit(javax.swing.JSplitPane.class);
	}

}
