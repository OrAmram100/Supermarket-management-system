package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class binFile {
	public static void writeStringToFile(String str, int size, DataOutput dO) throws IOException
	{
		char bit = 0;
		for (int i = size; i>=str.length(); i--) {
			dO.writeChar(bit);
		}
		for (int j = 0; j < str.length(); j++) 

		{
			bit = str.charAt(j);				
			dO.writeChar(bit);

		}			
	}

	public static String readStringFromFile(int size, DataInput dIn) throws IOException {
		StringBuffer b = new StringBuffer(size);
		for(int i=0; i < size;i++)
		{
			char ch = dIn.readChar();
			b.append(ch);
		}
		dIn.skipBytes(2 *size);
		return b.toString();
	}

//	public static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
//		ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
//		ObjectInput in = new ObjectInputStream(bis);
//		return in.readObject();
//	}
//
//	public static byte[] getByteArray(Object obj) throws IOException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
//			os.writeObject(obj);
//		}
//		return bos.toByteArray();
//	}


}
