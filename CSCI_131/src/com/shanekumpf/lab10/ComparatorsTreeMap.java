package com.shanekumpf.lab10;

import java.util.TreeMap;

import csci130.KeyboardReader;

public class ComparatorsTreeMap {
	public static final void main(String[] args) {
		TreeMap<String, Person> set = new TreeMap<String, Person>();

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

			if(newGuy.equals(set.get(id + firstName + lastName))) {
				System.out.println("\n*************************** ERROR *********************************\n");
				System.out.println("A person matching \"" + newGuy + "\" already exists.");
				System.out.println("This system only knows how to handle people with unique ID's.");
				System.out.println("Please try again");
			}
			System.out.println();
			
			set.put(id + firstName + lastName, newGuy);
		}

		System.out.println("\n\n\tYou entered the following people:\n\n");
		for(Person p : set.values()) {
			System.out.println(p);
		}
	}
}
