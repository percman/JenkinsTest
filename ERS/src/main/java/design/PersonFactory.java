package design;

import model.Employee;
import model.Manager;

public class PersonFactory {
	public static Person create(String type, int inEmployeeid, int inManagerid, String inUsername, String inPassword) {
		switch(type) {
		case "employee":
			return new Employee(inEmployeeid, inManagerid, inUsername, inPassword);
		case "manager":
			return new Manager(inEmployeeid, inManagerid, inUsername, inPassword);
		default:
			throw new Error("PersonFactory#create error");
		}
	}
}
