package command;

import model.Store;
import model.Store.SortType;

public class SortMapAccordingTypeCommand implements Command<Object> {
	private Store store;
	private SortType type;

	public SortMapAccordingTypeCommand(Store store, SortType type) {
		this.store = store;
		this.type = type;
	}

	@Override
	public Object execute() {
		store.sortMapAccordingType(type);
		return null;
	}

}
