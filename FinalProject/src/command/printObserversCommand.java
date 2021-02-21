package command;

import java.util.List;

import model.Product;
import model.Store;
import view.View;

public class printObserversCommand implements Command<Object> {
	private Store store;
	private List<Product>products;
	private View view;
	



	public printObserversCommand(Store store,List<Product> products,View view) {

		this.store = store;
		this.products=products;
		this.view=view;
	}


	@Override
	public Object execute() {
		 store.notifyObservers(products,view);
		return null;
		

	}
}
