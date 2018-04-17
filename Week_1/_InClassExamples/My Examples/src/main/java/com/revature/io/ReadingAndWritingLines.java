package com.revature.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadingAndWritingLines {

	static BufferedReader reader = null;
	static BufferedWriter writer = null;
	
	public static void readLinesFromFile(File file) {
		String s = null;
		try {
			reader = new BufferedReader(new FileReader(file.getPath()));
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			} finally {
				System.out.println("\n============================");
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static void writeLinesToFile(String toBeWritten, File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file.getPath()));
			writer.write(toBeWritten);
			System.out.println("Message Successfully written to " + file.getPath());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static void writeLinesFromAFileToAnotherFile(File fromFile, File toFile) {
		String s = null;
		int i = 0;
		try {
			reader = new BufferedReader(new FileReader(fromFile.getPath()));
			writer = new BufferedWriter(new FileWriter(toFile.getPath()));
			
			while ((s = reader.readLine()) != null) {
				writer.write(s + "\n");
				i++;
			}
			System.out.println(fromFile.getName() + " successfully read, successfully wrote " + i + " lines to " + toFile.getPath());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			} finally {
				System.out.println("Resources successfully closed");
			}
		}
	}
	
	
	public static void main(String[] args) {
		writeLinesFromAFileToAnotherFile(new File("src/main/resources/baseTextForFileIO.txt"), new File("src/main/resources/testLines"));
	}
}
