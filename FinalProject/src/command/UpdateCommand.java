package command;

import model.Product;
import model.Store;

public class UpdateCommand implements Command<Product>{
	private Store store;
	private String SerialNum;
	private int price;



	public UpdateCommand(Store store, String serialNum,int price) {
		this.price=price;
		this.store = store;
		SerialNum = serialNum;
	}



	@Override
	public Product execute() {
		return store.updateSale(SerialNum, price);

	}

}


