package com.revature.tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;


public class StringTokenizerExample {
	
	private static final Logger logger = Logger.getLogger(StringTokenizerExample.class);

	public static void printEmployeeData(File file, String delimiter) {
		BufferedReader reader = null;
		String s = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((s = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(s, delimiter);
				while (tokenizer.hasMoreTokens()) {
					System.out.println("Id: " + tokenizer.nextToken());
					System.out.println("Name: " + tokenizer.nextToken() + " " + tokenizer.nextToken());
					System.out.println("Hire Date: " + LocalDate.parse(tokenizer.nextToken(), formatter));
					System.out.println();
				}
			}
		} catch (FileNotFoundException fnfe) {
			logger.warn("Couldnt find your file: " + file.getName(), fnfe);
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
	}

	public static List<Employee> getEmployeeData(File file, String delimiter) {
		BufferedReader reader = null;
		String s;
		List<Employee> employees = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((s = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(s, delimiter);
				while (tokenizer.hasMoreTokens()) {
					employees.add(new Employee(
							new Integer(tokenizer.nextToken()),
							tokenizer.nextToken(),
							tokenizer.nextToken(),
							LocalDate.parse(tokenizer.nextToken())));
				}
			}
		} catch (FileNotFoundException fnfe) {
			logger.warn("Couldnt find your file: " + file.getName(), fnfe);
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
		return employees;
	}
	
	public static void main(String[] args) {
//		printEmployeeData(new File("src/main/resources/employeeData.txt"), ":");
		List<Employee> myEmployees = getEmployeeData(new File("src/main/resources/employeeData.txt"), ":");
		for (int i = 0; i < myEmployees.size(); i++) {
			System.out.println(myEmployees.get(i));
		}
	}
}
