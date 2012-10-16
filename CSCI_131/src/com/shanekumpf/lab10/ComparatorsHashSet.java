package com.shanekumpf.lab10;

import java.util.HashSet;

import csci130.KeyboardReader;

public class ComparatorsHashSet {
	public static final void main(String[] args) {
		HashSet<Person> set = new HashSet<Person>();

		String firstName = null;
		while(true) {
			System.out.println("Enter a person's first name or \"END\" to quit:");
			firstName = KeyboardReader.readLine();

			if(firstName.equalsIgnoreCase("END")) {
				break;
			}

			System.out.println("Enter the person's last name:");
			String lastName = KeyboardReader.readLine();
			System.out.println("Enter the person's id:");
			String id = KeyboardReader.readLine();

			Person newGuy = new Person(firstName, lastName, id);

			if(set.contains(newGuy)) {
				System.out.println("\n*************************** ERROR *********************************\n");
				System.out.println("A person matching \"" + newGuy + "\" already exists.");
				System.out.println("This system only knows how to handle people with unique ID's.");
				System.out.println("Please try again");
			}
			System.out.println();
			set.add(newGuy);
			
		}

		System.out.println("\n\n\tYou entered the following people:\n\n");
		for(Person p : set) {
			System.out.println(p);
		}
	}
}
