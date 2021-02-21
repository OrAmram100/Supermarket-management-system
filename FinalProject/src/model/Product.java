package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Product {
	private String productName;
	private int PriceToStore;
	private int PriceToCostumer;
	private Customer customer;
	private final static int MAX_NAME_SIZE = 20;
	public static final int PRODUCT_SIZE = (MAX_NAME_SIZE*2) + Customer.CUSTOMER_SIZE + 4 + 4;


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
	public static Product readProductFromFile(DataInput dIn) throws IOException
	{
		String name = binFile.readStringFromFile(MAX_NAME_SIZE, dIn);
		int sellingPrice = dIn.readInt();
		int buyingPrice = dIn.readInt();
		Customer c = Customer.readCustomerToFile(dIn);
		return new Product(name, buyingPrice, sellingPrice, c);
	}
	public void writeProductToFile(DataOutput dOut) throws IOException
	{
		binFile.writeStringToFile(productName, MAX_NAME_SIZE, dOut);
		dOut.writeInt(PriceToCostumer);
		dOut.writeInt(PriceToStore);
		customer.writeCustomerToFile(dOut);
	}

	@Override
	public String toString() {
		return "\nproductName:" + productName + ", PriceToStore:" + PriceToStore + ", PriceToCostumer:"
				+ PriceToCostumer + ", customer:" + customer;
	}

	
	
	
}
