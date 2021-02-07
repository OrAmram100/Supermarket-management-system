package model;

public class Product {
	private String productName;
	private int castToStore;
	private int costToCostumer;
	
	
	public Product(String productName, int castToStore, int costToCostumer) {
		this.productName = productName;
		this.castToStore = castToStore;
		this.costToCostumer = costToCostumer;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getCastToStore() {
		return castToStore;
	}


	public void setCastToStore(int castToStore) {
		this.castToStore = castToStore;
	}


	public int getCostToCostumer() {
		return costToCostumer;
	}


	public void setCostToCostumer(int costToCostumer) {
		this.costToCostumer = costToCostumer;
	}
	
	

}
