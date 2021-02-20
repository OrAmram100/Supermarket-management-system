package command;

import model.Store;

public class RemoveProductCommand implements Command<Integer> {
	private Store store;
	private String SerialNum;


	public RemoveProductCommand(Store store, String serialNum) {
		this.store = store;
		SerialNum = serialNum;
	}


	@Override
	public Integer execute() {
		return store.removeProduct(SerialNum,2);		
	}


}
