package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Store implements Observable {
	private static final long serialVersionUID = 1L; //  or please check
	public static final String FILE_NAME = "products txt";
	private ArrayList<Product> ProductList;  // or please check if we need it, this is the connection to iterator
	private Set<Customer> allCustomers; //  or please check
	private Map<String, Product> products;
	private Comparator<String> comparator;
//	private StoreProductsMomento productsMomento;
	int numOfProducts;
	CompareProductByAscendingIdGenartor compareAscending;
	CompareProductByDescendingIdGenartor compareDescending;
	
	
	
	
	public Store() {
		numOfProducts = 0;
		products =  new LinkedHashMap<String, Product>();
		compareAscending = new CompareProductByAscendingIdGenartor();
		compareDescending = new CompareProductByDescendingIdGenartor();
		setComperator(compareAscending); //default
	}
	
	
	
	
	public Iterator<Product> iterator() { // or please check if we need the iterator
		Iterator<Product> ProductIt = new Iterator<Product>() {

			private int index = -1;

			@Override
			public boolean hasNext() {
				return (index + 1) <  ProductList.size();
			}

			@Override
			public Product next() {
				return  ProductList.get(++index);
			}
		};
		return ProductIt;
	}

	

	public int readProductsFromBinaryFile(String fileName) {
		return 0;

	}

	public void addProduct(String key, Product p) {

	}
	
	
	public int removeProduct(String SerialNum) {
		products.remove(SerialNum);
		return 1;
	}
	
	public Product findProduct(String SerialNum){
		if(products.containsKey(SerialNum))
			return products.get(SerialNum);
		return null;
	}
	
	
	
	
	// GETTERS:
	public Map<String, Product> getProducts() {
		return products;
	}

	public int getNumOfProducts() {
		return numOfProducts;
	}

	public Comparator<String> getComparator() {
		return comparator;
	}

	// SETTERS:
	private void setComperator(Comparator<String> comparator) {
		this.comparator = comparator;

	}
	
	
	//COMPARATORS:
	public class CompareProductByAscendingIdGenartor implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}
	
	public class CompareProductByDescendingIdGenartor implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return (s1.compareTo(s2) * -1);
		}

	}
	
	// Print methods:
	
	public void printAllCustomersSet() {
		Iterator<Customer> it = allCustomers.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString() + "\n");
		}
	}
	
	public void printAllProducts() {
		setComperator(compareAscending);
//		sort();
		Iterator<Product> it = iterator();
		while (it.hasNext()) {
			Product product = it.next();
			System.out.println(product.toString()+"\n");
		}
	}

	public int saveProductsToBinaryFile(String fileName) 
	{

		try {
			ObjectOutputStream Object = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			Object.writeObject(products.size());
			for(java.util.Map.Entry<String, Product> e : products.entrySet())
			{
				Object.writeObject(e.getKey());
				Object.writeObject(e.getValue());
			}
			Object.close();
			return 1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	

	@Override
	public void addListener(InvalidationListener arg0) { // WTF is it?
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener arg0) {  // WTF is it?
		// TODO Auto-generated method stub

	}

}
