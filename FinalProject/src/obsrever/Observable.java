package obsrever;

import model.Product;

public interface Observable {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notifyObservers(Product product);
	void notifyObserver(Observer o, Product product);

}
