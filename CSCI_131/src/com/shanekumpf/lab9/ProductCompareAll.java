package com.shanekumpf.lab9;

import java.util.Comparator;

/**
 * Compares Products using the following rules. If price is equals, check
 * description, if description is equal check product number.
 * 
 * @author Shane Kumpf
 * @version 0.1 10/13/12
 * @since 1.6
 * 
 */
public class ProductCompareAll implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof Product) || !(o2 instanceof Product)) {
			throw new ClassCastException(
					"Supplied objects are not comparable to Products");
		}
		
		Product product1 = (Product) o1;
		Product product2 = (Product) o2;
		
		int priceCompare = Float.compare(product1.price, product2.price);
		if (priceCompare == 0) {
			int descCompare = product1.description
					.compareToIgnoreCase(product2.description);
			if (descCompare == 0) {
				return Integer.parseInt(Long.toString(product1.productNumber
						- product2.productNumber));
			}
		}
		return priceCompare;
	}
	
}
