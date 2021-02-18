package command;

import model.Store;

public class StoreCommand {
	private Command addProduct;
	private Command findProduct;
	private Command iteratorInFile;
	private Command readProductsToBinaryFile;
	private Command removeLastProduct;
	private Command removeProduct;
	private Command sortMapAccordingType;
	private Command writeProductsToBinaryFile;
	
	public StoreCommand(Store store) {
		this.addProduct = new AddProductCommand(null, null, null);
		this.findProduct = new FindProductCommand(null, null);
		this.iteratorInFile = new IteratorInFileCommand(null);
		this.readProductsToBinaryFile = new ReadProductsToBinaryFileCommand(null, null);
		this.removeLastProduct = new RemoveLastProductCommand(null);
		this.removeProduct = new RemoveProductCommand(null, null);
		this.sortMapAccordingType =new  SortMapAccordingTypeCommand(null, null);
		this.writeProductsToBinaryFile = new WriteProductsToBinaryFileCommand(null, null);
	}

	
	public void executeAddProduct() {
		this.addProduct = addProduct;
	}

	public Command getFindProduct() {
		return findProduct;
	}

	public void setFindProduct(Command findProduct) {
		this.findProduct = findProduct;
	}

	public Command getIteratorInFile() {
		return iteratorInFile;
	}

	public void setIteratorInFile(Command iteratorInFile) {
		this.iteratorInFile = iteratorInFile;
	}

	public Command getReadProductsToBinaryFile() {
		return readProductsToBinaryFile;
	}

	public void setReadProductsToBinaryFile(Command readProductsToBinaryFile) {
		this.readProductsToBinaryFile = readProductsToBinaryFile;
	}

	public Command getRemoveLastProduct() {
		return removeLastProduct;
	}

	public void setRemoveLastProduct(Command removeLastProduct) {
		this.removeLastProduct = removeLastProduct;
	}

	public Command getRemoveProduct() {
		return removeProduct;
	}

	public void setRemoveProduct(Command removeProduct) {
		this.removeProduct = removeProduct;
	}

	public Command getSortMapAccordingType() {
		return sortMapAccordingType;
	}

	public void setSortMapAccordingType(Command sortMapAccordingType) {
		this.sortMapAccordingType = sortMapAccordingType;
	}

	public Command getWriteProductsToBinaryFile() {
		return writeProductsToBinaryFile;
	}

	public void setWriteProductsToBinaryFile(Command writeProductsToBinaryFile) {
		this.writeProductsToBinaryFile = writeProductsToBinaryFile;
	}
	
	
	
	
	
	
	
	
}