package model;

public class Product {
	private String productName;
	private int PriceToStore;
	private int PriceToCostumer;
	private Customer customer;

	public Product(String productName, int castToStore, int costToCostumer, Customer customer) {
		this.productName = productName;
		this.PriceToStore = castToStore;
		this.PriceToCostumer = costToCostumer;
		this.customer = customer;
	}

	// GETTERS:
	public String getProductName() {
		return productName;
	}

	public int getPriceToStore() {
		return PriceToStore;
	}

	public int getPriceToCostumer() {
		return PriceToCostumer;
	}

	public Customer getCustomer() {
		return customer;
	}

	// SETTERS:
	public void setPriceToCostumer(int costToCostumer) {
		this.PriceToCostumer = costToCostumer;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPriceToStore(int costToStore) {
		this.PriceToStore = costToStore;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Product\nproductName:" + productName + ", PriceToStore:" + PriceToStore + ", PriceToCostumer:"
				+ PriceToCostumer + ", customer:" + customer;
	}

	
	
	
}
