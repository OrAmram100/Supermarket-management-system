package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.scene.control.Alert;

import java.util.Map.Entry;

import obsrever.Observable;
import obsrever.Observer;

public class Store implements Observable {
	private static Store _instance  = null;
	public static final int PRODUCT_KEY_MAX_SIZE = 9 ; 
	public enum SortType {eByAscending , eByDescending , eByIncome};
	public static final String FILE_NAME = "products.txt";
	private String name;
	private ArrayList<Observer> CustomersToUpdate;
	private Set<Customer> allCustomers; 
	private Map<String, Product> products;
	private Comparator<String> comparator;
	private Memento productsMomento;
	private int numOfProducts;
	SortType sortType;
	private CompareProductByAscendingIdGenartor compareAscending;
	private CompareProductByDescendingIdGenartor compareDescending;

	public static Store getInstance(String name) {
		if(_instance==null)
			_instance = new Store(name);
		return _instance;
	}



	public Store(String name) {
		numOfProducts = 0;
		products =  new LinkedHashMap<String, Product>();
		compareAscending = new CompareProductByAscendingIdGenartor();
		compareDescending = new CompareProductByDescendingIdGenartor();
		setComperator(compareAscending); //default
		allCustomers=new TreeSet<Customer>();
		CustomersToUpdate=new ArrayList<>();
		this.name=name;
		sortType = SortType.eByIncome;
		setComperator(null);
	}

	public void sortMapAccordingType(SortType type)
	{
		this.sortType=type;
		Map<String, Product> tempMap;
		switch(type) {

		case eByAscending:

			this.setComperator(compareAscending);
			tempMap = new TreeMap<String, Product>(comparator);
			tempMap.putAll(products);
			products= tempMap;
			break;

		case eByDescending:
			this.setComperator(compareDescending);
			tempMap = new TreeMap<String, Product>(comparator);
			tempMap.putAll(products);
			products =tempMap;
			break;


		case eByIncome:	
			products = new LinkedHashMap<>();
			this.setComperator(null);
			break;

		default:
			break;

		}	

	}
	public void iterationInFile(String SerialNum,int res) throws FileNotFoundException {
		Iterator<Entry<String, Product>> iterator = new FileIterator().getIterator(FILE_NAME);
		Entry<String, Product> entry ;
		switch(res)
		{

		case 1:
			entry = iterator.next();
			products.put(entry.getKey() , entry.getValue());
			break;

		case 2: 
			while(iterator.hasNext())
			{
				entry = iterator.next();
				if (entry.getKey().equalsIgnoreCase(SerialNum)) {
					iterator.remove();
					return;
				}
			}


		case 3: // remove all products
			while(iterator.hasNext()) {
				entry = iterator.next();
				iterator.remove();
				removeProduct(entry.getKey(),2);
			}

		}


	}


	public void addProduct(String key, Product p)
	{
		productsMomento = new Memento(products);
		products.put(key, p);
		this.numOfProducts++;
		if(p.getCustomer().isWantUpdates())
			addObserver(p.getCustomer());
		saveProductsToBinaryFile(FILE_NAME);

	}
	public int removeLastProduct()  {

		if(productsMomento== null)
			return 0;
		products = productsMomento.getProductsMap();
		productsMomento =null;

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("There is no product to delete");            

		return 1;

	}


	public int removeProduct(String SerialNum,int menu) {
		Product p = findProduct(SerialNum);
		if(p==null)
			return 0;
		products.remove(SerialNum);
		try {
			iterationInFile(SerialNum,menu);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public Product findProduct(String SerialNum){

		if(products.containsKey(SerialNum))
			return products.get(SerialNum);
		return null;
	}

	public Product findProductInFile(String serialNum) throws FileNotFoundException {
		Iterator<Entry<String, Product>> iterator = new FileIterator().getIterator(FILE_NAME);
		while (iterator.hasNext()) {
			Map.Entry<String, Product> entry = iterator.next();
			if (entry.getKey().equalsIgnoreCase(serialNum))
				return entry.getValue();

		}

		return null;

	}
	public void removeAllProducts(int menu) {
		try {
			iterationInFile(null, menu);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// GETTERS:
	public Map<String, Product> getProducts() {
		return products;
	}
	public String getName() {
		return name;
	}


	public int getNumOfProducts() {
		return numOfProducts;
	}

	public ArrayList<Observer> getCustomersToUpdate() {
		return CustomersToUpdate;
	}





	public Set<Customer> getAllCustomers() {
		return allCustomers;
	}



	public Comparator<String> getComparator() {
		return comparator;
	}

	// SETTERS:
	private void setComperator(Comparator<String> comparator) {
		this.comparator = comparator;

	}
	public void setName(String name) {
		this.name = name;
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


	public void printAllCustomersSet() {
		Iterator<Customer> it = allCustomers.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString() + "\n");
		}
	}

	public void printAllProducts() {
		setComperator(compareAscending);
		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Product> en = it.next();
			System.out.println(en.toString()+"\n");
		}
	}

	public int saveProductsToBinaryFile(String fileName) 
	{

		try {
			RandomAccessFile rF = new RandomAccessFile(FILE_NAME, "rw");

			for(Map.Entry<String, Product> entry : products.entrySet())
			{
				binFile.writeStringToFile(entry.getKey(), PRODUCT_KEY_MAX_SIZE, rF);
				entry.getValue().writeProductToFile(rF);
			}
			rF.close();
			return 1;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int readProductsFromBinaryFile(String fileName) throws IOException 
	{
			RandomAccessFile rF = new RandomAccessFile(FILE_NAME, "r");
			int size = (int)(rF.length() / (PRODUCT_KEY_MAX_SIZE + Product.PRODUCT_SIZE));
				for(int i =0 ; i < size ; i++)
				{
					String key = binFile.readStringFromFile(PRODUCT_KEY_MAX_SIZE, rF);
					Product p = Product.readProductFromFile(rF);
					products.put(key ,p);
				}

				rF.close();
				
			
		

		return 1;   
	}

	public int calculateProfit()
	{
		int sum=0;
		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) 
		{
			Entry<String, Product> en = it.next();
			sum+=en.getValue().getPriceToCostumer()-en.getValue().getPriceToStore();
		}
		return sum;
	}

	public void updateSale(String key,int newPrice)
	{
		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Product> en = it.next();
			if(en.getKey().equalsIgnoreCase(key))
			{
				en.getValue().setPriceToCostumer(newPrice);
				notifyObserver(en.getValue().getCustomer(),en.getValue());
			}
		}
	}
	@Override
	public void addObserver(Observer o)
	{
		CustomersToUpdate.add(o);

	}




	@Override
	public void deleteObserver(Observer o) 
	{
		CustomersToUpdate.remove(o);
	}




	@Override
	public void notifyObservers()    //print
	{
		for(Observer o: CustomersToUpdate)
		{
			Iterator<Entry<String, Product>> it = products.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Product> en = it.next();
				if(((Customer)o).getId().equalsIgnoreCase(en.getValue().getCustomer().getId()))
				{
					o.update(this, en.getValue());		
				}
			}
		}
	}




	@Override
	public void notifyObserver(Observer o, Product product) {
		o.update(this, product);
	}






}
