package obsrever;

import java.util.List;

import model.Product;
import view.View;

public interface Observable {
	void addObserver(Observer o);

	void deleteObserver(Observer o);

	void notifyObservers(List<Product> products, View view);

	void notifyObserver(Observer o, Product product);

}
