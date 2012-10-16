package com.shanekumpf.lab9;

import java.util.Comparator;

public class ProductCompareProductNum implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof Product) || !(o2 instanceof Product)) {
			throw new ClassCastException(
					"Supplied objects are not comparable to Products");
		}
		
		Product product1 = (Product) o1;
		Product product2 = (Product) o2;

		return Integer.parseInt(Long.toString(product1.productNumber
				- product2.productNumber));
	}

}
