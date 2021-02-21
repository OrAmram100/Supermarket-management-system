package command;

import java.util.List;

import controller.Controller;
import model.Product;
import model.Store;

public class printObserversCommand implements Command<String> {
	private Store store;
	private List<Product>products;
	



	public printObserversCommand(Store store,List<Product> products) {

		this.store = store;
		this.products=products;
		
	}


	@Override
	public String execute() {
		return store.notifyObservers(products);
		

	}
}
