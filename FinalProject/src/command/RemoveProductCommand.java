package command;

import model.Store;

public class RemoveProductCommand implements Command {
	private Store store;
	private String SerialNum;


	public RemoveProductCommand(Store store, String serialNum) {
		this.store = store;
		SerialNum = serialNum;
	}


	@Override
	public void execute() {
		store.removeProduct(SerialNum);		
	}


}
