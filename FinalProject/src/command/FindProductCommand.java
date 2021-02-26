package command;

import model.Product;
import model.Store;

public class FindProductCommand implements Command<Product> {
	private Store store;
	private String SerialNum;

	public FindProductCommand(Store store, String serialNum) {

		this.store = store;
		SerialNum = serialNum;
	}

	@Override
	public Product execute() {
		return store.findProduct(SerialNum);

	}

}
