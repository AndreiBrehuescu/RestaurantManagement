package BLL;

@SuppressWarnings("serial")
public class BaseProduct extends MenuItem {

	public BaseProduct(String name, float price) {
		super(name, price);
	}

	@Override
	public float computePrice() {
		return price;
	}

}
