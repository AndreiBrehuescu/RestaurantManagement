package BLL;

import java.util.HashSet;

public class CompositeProduct extends MenuItem {

	private HashSet<MenuItem> productParts;

	public CompositeProduct(String name, float price, HashSet<MenuItem> productParts) {
		super(name, price);
		this.productParts = productParts;
		this.price = computePrice();
	}

	public HashSet<MenuItem> getProductParts() {
		return productParts;
	}

	public void setProductParts(HashSet<MenuItem> productParts) {
		this.productParts = productParts;
	}

	@Override
	public float computePrice() {
		float totalPrice = 0;
		
		for (MenuItem menuItem : productParts) {
			totalPrice += menuItem.getPrice();
		}
		
		return totalPrice;
	}

}
