package command;

import model.Store;

public class printObserversCommand implements Command<Object> {
	private Store store;



	public printObserversCommand(Store store) {

		this.store = store;
	}



	@Override
	public Object execute() {
		store.notifyObservers();
		return null;

	}
}
