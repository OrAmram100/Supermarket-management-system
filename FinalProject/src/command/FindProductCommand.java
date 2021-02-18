package command;

import model.Store;

public class FindProductCommand implements Command {
	private Store store;
	private String SerialNum;



	public FindProductCommand(Store store, String serialNum) {

		this.store = store;
		SerialNum = serialNum;
	}



	@Override
	public void execute() {
		store.findProduct(SerialNum);

	}

}
