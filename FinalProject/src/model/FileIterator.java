package model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;


public class FileIterator {

	public Iterator<Entry<String, Product>> getIterator(String FileName) throws FileNotFoundException {
		return new ConcreteFileIterator(FileName);	
	}


	private class ConcreteFileIterator implements Iterator<Entry<String, Product>>{

		private int size;
		private RandomAccessFile rF;

		private int current =0;
		private int last =-1;

		public ConcreteFileIterator(String fileName) throws FileNotFoundException {
			try {
				rF = new RandomAccessFile(fileName, "rw");
				size = (int)(rF.length());				

			}catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public boolean hasNext() {
			return current<size;
		}

		@Override
		public Map.Entry<String, Product> next() {
			if (!hasNext())
				throw new NoSuchElementException();

			try {
				String idProduct =binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, rF);    //id product
				Product p = Product.readProductFromFile(rF);
				MyMap entryMap = new MyMap(idProduct, p);

				last = current;
				current+=(Store.PRODUCT_KEY_MAX_SIZE+Product.PRODUCT_SIZE);	
				return entryMap;

			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			try {
				while(hasNext()) {
					last=current;
					current+=(Store.PRODUCT_KEY_MAX_SIZE*2)+Product.PRODUCT_SIZE;
					rF.seek(current);
					String k = binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, rF);
					Product p = Product.readProductFromFile(rF);
					rF.seek(last);
					binFile.writeStringToFile(k, Store.PRODUCT_KEY_MAX_SIZE, rF);
					p.writeProductToFile(rF);
					rF.seek(current);

				}
				rF.setLength(size - (Store.PRODUCT_KEY_MAX_SIZE*2) - Product.PRODUCT_SIZE);
				last =-1;
				rF.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}



	private class MyMap implements Map.Entry<String, Product>{

		private String key;
		private Product value;

		public MyMap( String key ,  Product value) {
			this.key = key;
			this.value = value;

		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public Product getValue() {
			return value;
		}

		@Override
		public Product setValue(Product value) {
			Product old = this.value;
			this.value = value;
			return old;
		}

	}


}




