package design;

import model.Employee;
import model.Manager;

public class PersonFactory {
	public static Person create(String type, int id, String inUsername, String inPassword) {
		switch(type) {
		case "employee":
			return new Employee(id, inUsername, inPassword);
		case "manager":
			return new Manager(id, inUsername, inPassword);
		default:
			throw new Error("PersonFactory#create error");
		}
	}
}
