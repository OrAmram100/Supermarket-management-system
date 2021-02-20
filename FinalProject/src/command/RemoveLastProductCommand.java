package command;

import model.Store;

public class RemoveLastProductCommand implements Command<Integer> {

	private Store store;


	public RemoveLastProductCommand(Store store) {
		this.store = store;
	}


	@Override
	public Integer execute() {
		return store.removeLastProduct();
		
	}

	
}
