package command;

import java.io.FileNotFoundException;

import model.Store;

public class RemoveLastProductCommand implements Command<Integer> {

	private Store store;


	public RemoveLastProductCommand(Store store) {
		this.store = store;
	}


	@Override
	public Integer execute() {
		try {
			return store.removeLastProduct();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
}
