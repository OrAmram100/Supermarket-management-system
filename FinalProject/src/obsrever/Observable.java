package obsrever;

import java.util.List;

import model.Product;

public interface Observable {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	String notifyObservers(List<Product>products );
	void notifyObserver(Observer o, Product product);

}
