package model;


public class StoreMemento {
		
		public Memento createMemento(Product p) {
			return new Memento(p);
		}

			
		public static class Memento {
			private Product product;

			public Memento(Product products) {
				this.product = products;
			}

			public Product getProduct() {
				return product;
			}
		}
	}
	

