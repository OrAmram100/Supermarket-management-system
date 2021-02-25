package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.Map.Entry;

import obsrever.Observable;
import obsrever.Observer;
import view.View;

public class Store implements Observable {
	private static Store _instance = null;
	public static final int PRODUCT_KEY_MAX_SIZE = 9;

	public enum SortType {
		eByAscending, eByDescending, eByIncome
	};

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
	private ComperatorProductByInsert compareByIncome;

	public static Store getInstance(String name) {
		if (_instance == null)
			_instance = new Store(name);
		return _instance;
	}

	public Store(String name) {
		numOfProducts = 0;
		products = new LinkedHashMap<String, Product>();
		compareAscending = new CompareProductByAscendingIdGenartor();
		compareDescending = new CompareProductByDescendingIdGenartor();
		setComperator(compareAscending); // default
		allCustomers = new TreeSet<Customer>();
		CustomersToUpdate = new ArrayList<>();
		this.name = name;
		sortType = SortType.eByIncome;
		setComperator(null);
	}

	public void sortMapAccordingType(SortType type) {
		this.sortType = type;
		Map<String, Product> tempMap;
		switch (type) {

		case eByAscending:

			this.setComperator(compareAscending);
			tempMap = new TreeMap<String, Product>(comparator);
			tempMap.putAll(products);
			removeAllProducts(3);
			for (Iterator<Entry<String, Product>> iterator = tempMap.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Product> entry = iterator.next();
				addProduct(entry.getKey(), entry.getValue());
			}
			products = tempMap;
			break;

		case eByDescending:
			this.setComperator(compareDescending);
			tempMap = new TreeMap<String, Product>(comparator);
			tempMap.putAll(products);
			removeAllProducts(3);
			for (Iterator<Entry<String, Product>> iterator = tempMap.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Product> entry = iterator.next();
				addProduct(entry.getKey(), entry.getValue());
			}
			products = tempMap;
			break;

		case eByIncome:
			this.setComperator(compareByIncome);
			tempMap = new TreeMap<String, Product>(comparator);
			tempMap.putAll(products);
			removeAllProducts(3);
			for (Iterator<Entry<String, Product>> iterator = tempMap.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Product> entry = iterator.next();
				addProduct(entry.getKey(), entry.getValue());
			}
			products = new LinkedHashMap<String, Product>(tempMap);
			break;

		default:
			break;

		}

	}

	public void iterationInFile(String SerialNum, int res) throws FileNotFoundException {
		Iterator<Entry<String, Product>> iterator = new FileIterator().getIterator(FILE_NAME);
		Entry<String, Product> entry;
		switch (res) {

		case 1:
			entry = iterator.next();
			products.put(entry.getKey(), entry.getValue());
			break;

		case 2:
			while (iterator.hasNext()) {
				entry = iterator.next();
				if (entry.getKey().equalsIgnoreCase(SerialNum)) {
					iterator.remove();
					return;
				}
			}

		case 3: // remove all products
			while (iterator.hasNext()) {
				entry = iterator.next();
				iterator.remove();
				products.remove(entry.getKey());
				iterator = new FileIterator().getIterator(FILE_NAME);
			}

		}

	}

	public void addProduct(String key, Product p) {
		productsMomento = new Memento(products);
		products.put(key, p);
		this.numOfProducts++;
		if (p.getCustomer().isWantUpdates())
			addObserver(p.getCustomer());
		saveProductsToBinaryFile(FILE_NAME);

	}

	public int removeLastProduct() throws FileNotFoundException {

		if (productsMomento == null)
			return 0;
		String lastKey = getLastKey();
		iterationInFile(lastKey, 2);

		products = productsMomento.getProductsMap();
		productsMomento = null;

		return 1;

	}

	private String getLastKey() {
		String lastObject = "";
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			lastObject = entry.getKey();
		}
		return lastObject;
	}

	public int removeProduct(String SerialNum, int menu) {
		Product p = findProduct(SerialNum);
		if (p == null)
			return 0;
		products.remove(SerialNum);
		try {
			iterationInFile(SerialNum, menu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		numOfProducts--;

		return 1;
	}

	public Product findProduct(String SerialNum) {

		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Product> en = it.next();
			if (en.getKey().equalsIgnoreCase(SerialNum))
				return products.get(SerialNum);
		}
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
			e.printStackTrace();
		}
	}

	// GETTERS:
	public Map<String, Product> getProducts() {
		return products;
	}

	public String getNameStore() {
		return name;
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

	public void setNameStore(String name) {
		this.name = name;
	}

	// COMPARATORS:
	public class CompareProductByAscendingIdGenartor implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}

	public class CompareProductByDescendingIdGenartor implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return (s2.compareTo(s1));
		}

	}

	public class ComperatorProductByInsert implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			return 1;
		}

	}

	public int getNumOfProducts() {
		return numOfProducts;
	}

	public Map<String, Product> getMap() {
		return products;
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
			System.out.println(en.toString() + "\n");
		}
	}

	public int saveProductsToBinaryFile(String fileName) {

		try {
			RandomAccessFile rF = new RandomAccessFile(FILE_NAME, "rw");

			for (Map.Entry<String, Product> entry : products.entrySet()) {
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

	public int readProductsFromBinaryFile(String fileName) throws IOException {
		RandomAccessFile rF = new RandomAccessFile(FILE_NAME, "r");
		int size = (int) (rF.length() / (PRODUCT_KEY_MAX_SIZE + Product.PRODUCT_SIZE));
		for (int i = 0; i < size; i++) {
			numOfProducts++;
			String key = binFile.readStringFromFile(PRODUCT_KEY_MAX_SIZE, rF);
			Product p = Product.readProductFromFile(rF);
			products.put(key, p);
			if (p.getCustomer().isWantUpdates()) {
				CustomersToUpdate.add(p.getCustomer());
			}
		}

		rF.close();

		return 1;
	}

	public int calculateProfit() {
		int sum = 0;
		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Product> en = it.next();
			sum += en.getValue().getPriceToCostumer() - en.getValue().getPriceToStore();
		}
		return sum;
	}

	public Product updateSale(String key, int newPrice) {
		Iterator<Entry<String, Product>> it = products.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Product> en = it.next();
			if (en.getKey().equalsIgnoreCase(key)) {
				en.getValue().setPriceToCostumer(newPrice);
				return en.getValue();
			}
		}
		return null;
	}

	@Override
	public void addObserver(Observer o) {
		CustomersToUpdate.add(o);

	}

	@Override
	public void deleteObserver(Observer o) {
		CustomersToUpdate.remove(o);
	}

	public class updateObservers extends Thread {
		private List<Product> products;
		private View view;
		private Store store;

		public updateObservers(List<Product> products, View view, Store store) // print
		{
			this.products = products;
			this.view = view;
			this.store = store;
		}

		@Override
		public void run() {
			if (products.size() >= 1) {
				for (int i = 0; i < products.size(); i++) {
					for (Observer o : CustomersToUpdate) {

						view.printLabels(o.update(store, products.get(i)) + "\n");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				products.clear();
			}
		}
	}

	@Override
	public void notifyObserver(Observer o, Product product) {
		o.update(this, product);
	}

	@Override
	public void notifyObservers(List<Product> products, View view) {
		Thread t = new Thread(() -> {
			try {
				updateObservers msg = new updateObservers(products, view, this);

				msg.run();
			} catch (Exception e) {
				view.warningStage();
			}
		});
		t.start();
	}

}
