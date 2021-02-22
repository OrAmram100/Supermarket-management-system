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

	private class ConcreteFileIterator implements Iterator<Entry<String, Product>> {

		private int size;
		private RandomAccessFile rF;
		private int current = 0;
		private int last = -1;
		private int productSize = Store.PRODUCT_KEY_MAX_SIZE * 2 + Product.PRODUCT_SIZE;

		public ConcreteFileIterator(String fileName) throws FileNotFoundException {
			try {
				rF = new RandomAccessFile(fileName, "rw");
				size = (int) (rF.length());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Map.Entry<String, Product> next() {
			if (!hasNext())
				throw new NoSuchElementException();

			try {
				String idProduct = binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, rF); // id product
				Product p = Product.readProductFromFile(rF);
				MyMap entryMap = new MyMap(idProduct, p);

				last = current;
				current += productSize;
				return entryMap;

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			try {
				if (size <= (productSize)) {
					rF.setLength(0);
					last = -1;
					rF.close();
					return;
				}
				while (hasNext() && (size !=current)) {
					int pos = last;
					pos += productSize;
					rF.seek(pos);
					String key = binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, rF);
					Product product = Product.readProductFromFile(rF);
					rF.seek(last);
					binFile.writeStringToFile(key, Store.PRODUCT_KEY_MAX_SIZE, rF);
					product.writeProductToFile(rF);
					last = (int) rF.getFilePointer();
					current+=productSize;

				}
				rF.setLength(size - productSize);
				last = -1;
				rF.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private class MyMap implements Map.Entry<String, Product> {

			private String key;
			private Product value;

			public MyMap(String key, Product value) {
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
}

//package model;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//import model.Product;
//import model.Store;
//import model.binFile;
//
//public class FileIterator {
//	
//	public Iterator<Map.Entry<String , Product>> getIterator(String FileName) {
//		return new ConcreteFileIterator(FileName);	
//	}
//
//
//	private class ConcreteFileIterator implements Iterator<Map.Entry<String , Product>>{
//		
//		private File f;
//		private int size;
//		private RandomAccessFile raf;
//		private byte[] binKey;
//		private byte[] binProduct;
//		private int singleEntitySize = (Store.PRODUCT_KEY_MAX_SIZE*2) + Product.PRODUCT_SIZE;
//		
//		private int current =0;
//		private int last =-1;
//		
//		public ConcreteFileIterator(String fileName) {
//			f = new File(fileName);
//			try {
//				raf = new RandomAccessFile(fileName, "rw");
//				size = (int)(raf.length());// / (Store.PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));				
//				
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
//
//		@Override
//		public boolean hasNext() {
//			return current<size;
//		}
//
//		@Override
//		public Map.Entry<String, Product> next() {
//			if (!hasNext())
//				throw new NoSuchElementException();
//
//			try {
//				String k = binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, raf);
//				Product p = Product.readProductFromFile(raf);
//				MyEntry entry = new MyEntry(k, p);
//				
//				last = current;
//				current += singleEntitySize;	
//				return entry;
//			}
//			catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		public void remove() {
//			if (last == -1)
//				throw new IllegalStateException();
//
//			try {
//				if(size<=singleEntitySize) {
//					raf.setLength(0);
//					last =-1;
//					raf.close();
//					return;
//				}
//				while(hasNext()&&(size>current+singleEntitySize)) {
//					int pos = last;
//					pos+=singleEntitySize;
//					raf.seek(pos);
//					String k = binFile.readStringFromFile(Store.PRODUCT_KEY_MAX_SIZE, raf);
//					Product p = Product.readProductFromFile(raf);
//					raf.seek(last);
//					binFile.writeStringToFile(k,Store.PRODUCT_KEY_MAX_SIZE, raf);
//					p.writeProductToFile(raf);
//					last=(int) raf.getFilePointer();
//					current = (int) raf.getFilePointer();
//					
//				}
//				raf.setLength(size - singleEntitySize);
//				last =-1;
//				raf.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		
//		
//		private class MyEntry implements Map.Entry<String, Product>{
//			
//			private String key;
//			private Product value;
//			
//			public MyEntry(final String key ,  Product value) {
//				this.key = key;
//				this.value = value;
//			
//			}
//
//			@Override
//			public String getKey() {
//				return key;
//			}
//
//			@Override
//			public Product getValue() {
//				return value;
//			}
//
//			@Override
//			public Product setValue(Product value) {
//				Product old = this.value;
//				this.value = value;
//				return old;
//			}
//
//		}
//	}
//
//
//
//}
