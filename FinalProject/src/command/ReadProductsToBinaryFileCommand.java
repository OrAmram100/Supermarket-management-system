package command;

import java.io.IOException;

import model.Store;

public class ReadProductsToBinaryFileCommand implements Command<Integer> {

	private Store store;
	private String fileName;
	
	
	
	public ReadProductsToBinaryFileCommand(Store store, String fileName) {
		this.store = store;
		this.fileName = fileName;
	}



	@Override
	public Integer execute() {
		try {
			return store.readProductsFromBinaryFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
