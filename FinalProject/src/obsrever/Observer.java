package obsrever;

import model.Product;

public interface Observer {
	void update(Observable obs, Product product);
}
