package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class binFile {
	public static void writeStringToFile(String str, int size, DataOutput dO) throws IOException {
		int i = 0;
		while (i < size) {
			char bit = 0;
			if (i < str.length())
				bit = str.charAt(i);
			dO.writeChar(bit);
			i++;
		}
	}

	public static String readStringFromFile(int size, DataInput dIn) throws IOException {
		StringBuilder b = new StringBuilder(size);
		int i;
		for (i = 0; i < size; ++i) {
			char ch = dIn.readChar();
			if (ch == 0)
				break;
			else
				b.append(ch);
		}
		dIn.skipBytes(2 * (size - i - 1));
		return b.toString();

	}

}
