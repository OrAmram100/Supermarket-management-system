package command;

import model.Store;

public class ReadProductsToBinaryFileCommand implements Command {

	private Store store;
	private String fileName;
	
	
	
	public ReadProductsToBinaryFileCommand(Store store, String fileName) {
		this.store = store;
		this.fileName = fileName;
	}



	@Override
	public void execute() {
		store.readProductsFromBinaryFile(fileName);
		
	}

}
