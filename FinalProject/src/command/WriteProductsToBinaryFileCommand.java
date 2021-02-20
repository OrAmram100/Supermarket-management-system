package command;

import model.Store;

public class WriteProductsToBinaryFileCommand implements Command<Integer>{
	private Store store;
	private String fileName;



	public WriteProductsToBinaryFileCommand(Store store, String fileName) {
		this.store = store;
		this.fileName = fileName;
	}



	@Override
	public Integer execute() {
		return store.saveProductsToBinaryFile(fileName);

	}

}
