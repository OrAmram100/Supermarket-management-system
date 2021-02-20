package obsrever;

import model.Product;

public interface Observer {
	String update(Observable obs, Product product);
}
