package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadingAndWritingBytes {

	static OutputStream out = null;
	static InputStream in = null;
	
	public static void writeToFileWithBytes(String toBeWritten, File file) {
		try {
			out = new FileOutputStream(file);
			byte[] bytes = toBeWritten.getBytes();
			for (int i = 0; i < bytes.length; i++) {
				System.out.println(bytes[i]);
			}
			out.write(bytes);
			System.out.println("Message successfully written to " + file.getPath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				out.close();
				System.out.println("Resource successfully closed");
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
		}
	}
	
	public static void readBytesFromFile(File file) {
		int i = 0;
		char c = 0;
		try {
			in = new FileInputStream(file);
			while ((i = in.read()) != -1) {
				c = (char) i;
				System.out.print(c);
			}
			System.out.println();
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				in.close();
				System.out.println("Resource successfully closed");
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
		}
	}
	
	public static void writeBytesFromFileToAnotherFile(File fromFile, File toFile) {
		int i = 0;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			
			while ((i = in.read()) != -1) {
				System.out.println(i);
				out.write(i);
			}
			System.out.println(fromFile.getName() + " successfully read, written to " + toFile.getPath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				in.close();
				out.close();
				System.out.println("Resources successfully closed");
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
		}
	}
}
