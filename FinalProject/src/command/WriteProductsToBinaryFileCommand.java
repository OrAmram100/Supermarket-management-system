package command;

import model.Store;

public class WriteProductsToBinaryFileCommand implements Command{
	private Store store;
	private String fileName;



	public WriteProductsToBinaryFileCommand(Store store, String fileName) {
		this.store = store;
		this.fileName = fileName;
	}



	@Override
	public void execute() {
		store.saveProductsToBinaryFile(fileName);

	}

}
