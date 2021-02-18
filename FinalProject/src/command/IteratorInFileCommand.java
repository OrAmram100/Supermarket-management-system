package command;

import java.io.FileNotFoundException;

import model.Store;

public class IteratorInFileCommand implements Command {
	private Store store;


	public IteratorInFileCommand(Store store) {
		this.store = store;
	}


	@Override
	public void execute() {
		try {
			store.iterationInFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
