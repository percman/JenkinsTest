package com.revature.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadingAndWritingCharacters {

	static FileReader reader = null;
	static FileWriter writer = null;
	
	public static void writeCharsToFile(String toBeWritten, File file) {
		try {
			writer = new FileWriter(file);
			writer.write(toBeWritten.toCharArray());
			System.out.println("Message successfully written to " + file.getPath());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static void readCharsFromFile(File file) {
		int i = 0;
		try {
			reader = new FileReader(file);
			while ((i = reader.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static void writeCharsFromFileToAnotherFile(File fromFile, File toFile) {
		int i = 0;
		try {
			reader = new FileReader(fromFile);
			writer = new FileWriter(toFile);
			
			while ((i = reader.read()) != -1) {
				writer.write(i);
			}
			System.out.println(fromFile.getName() + " successfully read, written to " + toFile.getPath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resources successfully closed");
			}
		}
	}
	
	public static void main(String[] args) {
		writeCharsFromFileToAnotherFile(new File("src/main/resources/writingChars"), new File("src/main/resources/testWritingChars"));
	}
}
