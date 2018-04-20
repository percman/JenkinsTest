package com.revature.builder;

import java.time.LocalDate;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate DOB;
	private int SSN;
	private String occupation;
	private LocalDate startDate;
	private int salary;
	private String department;
	private String address;
	private String phoneNumber;
	private boolean eligibleForPTO;


	public Employee(EmployeeBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.DOB = builder.DOB;
		this.SSN = builder.SSN;
		this.occupation = builder.occupation;
		this.startDate = builder.startDate;
		this.salary = builder.salary;
		this.department = builder.department;
		this.address = builder.address;
		this.phoneNumber = builder.phoneNumber;
		this.eligibleForPTO = builder.eligibleForPTO;
	}
	
	static class EmployeeBuilder{
		private int id;
		private String firstName;
		private String lastName;
		private String email;
		private LocalDate DOB;
		private int SSN;
		private String occupation;
		private LocalDate startDate;
		private int salary;
		private String department;
		private String address;
		private String phoneNumber;
		private boolean eligibleForPTO;
		
		public EmployeeBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public EmployeeBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public EmployeeBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public EmployeeBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public EmployeeBuilder DOB(LocalDate DOB) {
			this.DOB = DOB;
			return this;
		}
		
		public EmployeeBuilder SSN(int SSN) {
			this.SSN = SSN;
			return this;
		}
		
		public EmployeeBuilder occupation(String occupation) {
			this.occupation = occupation;
			return this;
		}
		
		public EmployeeBuilder startDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public EmployeeBuilder salary(int salary) {
			this.salary = salary;
			return this;
		}
		
		public EmployeeBuilder department(String department) {
			this.department = department;
			return this;
		}
		
		public EmployeeBuilder address(String address) {
			this.address = address;
			return this;
		}
		
		public EmployeeBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public EmployeeBuilder eligibleForPTO(Boolean eligibleForPTO) {
			this.eligibleForPTO = eligibleForPTO;
			return this;
		}
		
		
		
		public Employee build() {
			return new Employee(this);
		}

		}
	
	
	@Override
	public String toString() {
		return "EmployeeBuilder [id=" + id + ", \n firstName=" + firstName + ", \n lastName=" + lastName + ",  \n email="
				+ email + ", \n DOB=" + DOB + ", \n SSN=" + SSN + ", \n occupation=" + occupation + ", \n startDate="
				+ startDate + ", \n salary=" + salary + ", \n department=" + department + ", \n address=" + address
				+ ", \n phoneNumber=" + phoneNumber + ", \n eligibleForPTO=" + eligibleForPTO + "]";

	}
	
	
	public static void main(String[] args) {
		Employee e1 = new Employee.EmployeeBuilder().id(13).firstName("Tom").lastName("Jones")
				.email("email@test.test").DOB(LocalDate.of(1999, 1, 1)).SSN(123456789)
				.occupation("Software Developer").startDate(LocalDate.now()).salary(1000000)
				.department("Technology").address("123 Main Street").phoneNumber("987-654-3210")
				.eligibleForPTO(false).build();
		
		System.out.println(e1);
	}

}
