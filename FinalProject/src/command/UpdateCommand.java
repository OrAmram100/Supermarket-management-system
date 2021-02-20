package command;

import model.Store;

public class UpdateCommand implements Command<Object>{
	private Store store;
	private String SerialNum;
	private int price;



	public UpdateCommand(Store store, String serialNum,int price) {
		this.price=price;
		this.store = store;
		SerialNum = serialNum;
	}



	@Override
	public String execute() {
		store.updateSale(SerialNum, price);
		return null;

	}

}


