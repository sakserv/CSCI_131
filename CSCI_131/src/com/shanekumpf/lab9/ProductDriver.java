package com.shanekumpf.lab9;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import csci130.KeyboardReaderNG;

public class ProductDriver {

	public final static void main(String[] args) {

		// Add dummy products
		Vector<Product> vector = new Vector<Product>();
		vector.add(new Product(123456, "Large Toy", 9.99f));
		vector.add(new Product(123453, "Large Toy", 9.99f));
		vector.add(new Product(123453, "Large Toy", 9.99f));
		vector.add(new Product(123453, "Medium Toy", 9.99f));
		vector.add(new Product(123453, "Medium Toy", 10.99f));
		vector.add(new Product(123456, "Small Toy", 9.98f));
		vector.add(new Product(123456, "Small Toy", 9.98f));
		vector.add(new Product(123456, "Small Toy", 9.99f));
		vector.add(new Product(123455, "Small Toy", 9.99f));

		// Display menu to user
		System.out.println("1) Sort by Description");
		System.out.println("2) Sort by Product Number");
		System.out.println("3) Sort by Price");
		System.out.print("Enter the number to indicate how "
				+ "products should be compared and sorted: ");

		int input = KeyboardReaderNG.readInt();
		while (input > 3 || input < 0) {
			System.out.print("Invalid response, try again: ");
			input = KeyboardReaderNG.readInt();
		}

		// Setup the TreeSet
		SortedSet<Product> products = null;
		if (input == 1) {
			products = new TreeSet<Product>(vector);
		} else if (input == 2) {
			products = new TreeSet<Product>(new ProductCompareProductNum());
			products.addAll(vector);
		} else {
			products = new TreeSet<Product>(new ProductCompareAll());
			products.addAll(vector);
		}

		// Display the sorted products
		for (Product product : products) {
			System.out.println("Desc: " + product.description + " - Price: "
					+ product.price + " - Prod Num: " + product.productNumber);
		}
	}

}
