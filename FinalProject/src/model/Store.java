package model;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Store implements Observable {
	private String storeName;
	private List<Product> products;
	private List<Customer> customers;
	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
