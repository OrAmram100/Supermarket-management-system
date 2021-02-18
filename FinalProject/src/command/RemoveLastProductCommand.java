package command;

import model.Store;

public class RemoveLastProductCommand implements Command {

	private Store store;


	public RemoveLastProductCommand(Store store) {
		this.store = store;
	}

	@Override
	public void execute() {
		store.removeLastProduct();		
	}

}
