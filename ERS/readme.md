Project 0 Requirements
=================================================
persistence
user/creation login
admin
users approved
admin can lock
transactions
utilize logging/unit tests

Project 0-1 Requirements
=================================================
use jdbc for persistence
use a dao
3 stored procedures
2 udf
must only use preparedstatements and callablestatements
2 sequences
2 triggers
create custom checked exceptions where applicable
single and factory
tables must be in 3nf
must include startup sql script
due Monday, April 30, 9:00am
-- use prepared statements for delete in jdbc

Project 1 Requirements
=================================================
Expense Reimbursement System (ERS)
	The ERS will manage the process of reimbursing employees for expenses incurred while on company time. 
	
Requirements
	Employees can log in 
	Employees can log out 
	Employees can view the Employee homepage 
	Employees can view their information
	Employees can update their information 
	Employees can submit reimbursements
		Reimbursements fall into the category of lodging, travel, food, and other
	Employees can view their pending requests
	Employees can view their resolved requests 
	Employees can upload an image of his/her reciept as part of the reimbursement request (HARD! Do last)
	
	
	Finance managers can log in
	Finance managers can log out 
	Finance managers can view the Manager homepage 
	Finance managers can approve/deny reimbursements
	Finance managers can view all Employees 
	Finance managers can view all reimbursements from all Employees
	Finance managers can view all reimbursements from a single Employee 
	Finance managers can view which manager approved/denied reimbursements
	Finance managers can not approve/deny their own requests 
	Finance managers can view images of the reciepts from reimbursement requests (HARD! Do last)
	
	
Technnologies
	- Java 8
	- SQL and PL/SQL
	- JDBC
	- Servlets
	- HTML/CSS/JavaScript
	- Bootstrap
	- AJAX (at least 2 functionalities)
	- jUnit
	- log4j
	- JSPs (optional)
	- Angular (optional)
	
Environment
	- Spring Tool Suites/Eclipse
	- Oracle 12c Database
	- SQL Developer
	- Tomcat 
	
Structural Requirements
	- Design Patterns
		- Singleton
		- Factory
		- Data Access Object
		- Front Controller 