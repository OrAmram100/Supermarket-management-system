package obsrever;

import model.Product;

public interface Observable {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notifyObservers();
	void notifyObserver(Observer o, Product product);

}
