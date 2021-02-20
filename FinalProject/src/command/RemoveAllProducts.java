package command;

import model.Store;

public class RemoveAllProducts implements Command<Object>{
		private Store store;

		public RemoveAllProducts(Store store) {
			this.store = store;
		}



		

	@Override
	public Object execute() {
		store.removeAllProducts(3);
		return null;
	}
}


