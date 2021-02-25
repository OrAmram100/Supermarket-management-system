package command;

import model.Product;
import model.Store;

public class AddProductCommand implements Command<String>{

	private Store store;
	private String key;
	private Product product;

	public AddProductCommand(Store store,String key , Product product)
	{
		this.store = store;
		this.key = key;
		this.product = product;
	}

	@Override
	public String execute() {
		 store.addProduct(key, product);
		 return key;

	}

}