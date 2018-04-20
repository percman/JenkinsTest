package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadingAndWritingBytes {
	
	static FileInputStream in;
	static FileOutputStream out;
	
	public static void writeBytesToFile(String toBeWritten, File file) {
		try {
			out = new FileOutputStream(file);
			byte[] bytes = toBeWritten.getBytes();
			for(int i = 0; i < bytes.length; i++) {
				System.out.println(bytes[i]);
			}
			out.write(bytes);
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("Could not find file " + file.getName());
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public static void writeBytesToAFileFromAFile(File destination, File resource) {
		// Since Java 7, they included the try-with-resources functionality which will automatically
		// close your resources in the reverse order in which they were declared
		int i = 0;
		
		try(FileInputStream in = new FileInputStream(resource); FileOutputStream out = new FileOutputStream(destination);){
			while((i = in.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("Finally blocks will always execute, unless System.exit() or catastrophic event occurs");
		}
	}
	
	
	public static void main(String[] args) {
		// writeBytesToFile("Hey! This is Amelia, I'm writing bytes", new File("src/main/resources/writingBytes.txt"));
		
		writeBytesToAFileFromAFile(new File("src/main/resources/testBytes.txt"), new File("src/main/resources/dummy.txt"));
		
	}

}
