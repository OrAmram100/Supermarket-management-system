package model;

public class IdGenerator {

	private static IdGenerator _instance;
	private int serialNumber;

	private IdGenerator() {
		this.serialNumber = 0;
	}

	public static IdGenerator getInstance() {
		if (_instance == null)
			_instance = new IdGenerator();
		return _instance;
	}

	public int getNumber() {
		return serialNumber;
	}

	public int getNext() {
		return ++serialNumber;
	}

	@Override
	public String toString() {
		return "serial number = " + serialNumber;
	}

}
