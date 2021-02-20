package command;

import java.util.Map;

import model.Product;
import model.Store;

public class GetMapProducts implements Command<Map<String, Product>>{
		private Store store;

		public GetMapProducts(Store store) {
			this.store = store;
		}



		@Override
		public Map<String, Product> execute() {
			return store.getProducts();
		}


	}

