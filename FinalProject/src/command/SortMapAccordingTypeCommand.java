package command;

import model.Store;
import model.Store.SortType;

public class SortMapAccordingTypeCommand implements Command {
	private Store store;
	private SortType type;

	public SortMapAccordingTypeCommand(Store store, SortType type) {
		this.store = store;
		this.type = type;
	}
	
	@Override
	public void execute() {
	store.sortMapAccordingType(type);
	}
	
	
}
