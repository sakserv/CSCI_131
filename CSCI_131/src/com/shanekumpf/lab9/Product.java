package com.shanekumpf.lab9;

/**
 * Provides the ability to create and compare Products
 * 
 * @author Shane Kumpf
 * @verion 0.1 10/13/12
 * @since 1.6
 * 
 */
public class Product implements Comparable<Object> {
	String description;
	float price;
	long productNumber;

	/**
	 * Sets object properties
	 * 
	 * @param productNumber
	 *            product number
	 * @param description
	 *            description of the product
	 * @param price
	 *            price of the product
	 */
	public Product(long productNumber, String description, float price) {
		this.description = description;
		this.price = price;
		this.productNumber = productNumber;
	}

	/**
	 * Compares Products for equality. The rules are as follows: if the
	 * descriptions are the same, the products are the same, otherwise if the
	 * prices are the same, the products are the same, otherwise if the product
	 * numbers are the same, the products are the same.
	 * 
	 * @param o
	 *            the Product object that is compared to this object
	 * @return true if the objects match using the criteria above
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Product)) {
			return false;
		}
		Product product = (Product) o;
		return product.description.equalsIgnoreCase(description)
				&& product.price == price
				&& product.productNumber == productNumber;
	}

	/**
	 * Hash code for Products
	 * 
	 * @return the hash code for the product
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + description.hashCode();
		result = 31 * result + Integer.parseInt(Float.toString(price));
		result = 31 * result + Integer.parseInt(Long.toString(productNumber));
		return result;
	}

	/**
	 * Compares two objects for sorting using the standard contract for
	 * compareTo
	 * 
	 * See {@link Product.equals} for how equality is determined.
	 * 
	 * @param o
	 *            object being compared to this
	 * @return 0 if the objects are equal, < 0 if this becomes before o, > 0 if
	 *         o comes before this.
	 */
	@Override
	public int compareTo(Object o) {
		// optimization, if objects are equal, stop here
		if (this == o) {
			return 0;
		}

		if (!(o instanceof Product)) {
			throw new ClassCastException(
					"Provided object is not comparable with Product");
		}
		Product product = (Product) o;

		int descCompare = this.description
				.compareToIgnoreCase(product.description);

		// Descriptions match, check price
		if (descCompare == 0) {

			int priceCompare = Float.compare(this.price, product.price);

			// Price matches, check product number
			if (priceCompare == 0) {

				// Product number check
				return Integer.parseInt(Long.toString(this.productNumber
							- product.productNumber));
				
			// Description matches but price does not
			} else {
				return priceCompare;
			}
		}

		// Description does not match
		return descCompare;
	}
}
