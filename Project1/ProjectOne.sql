--Create an admins
CREATE USER cameronskaggs identified by mypassword;
--Grant privileges
GRANT INSERT ON employeeTable TO cameron;
GRANT UPDATE ON employeeTable TO cameron;
GRANT SELECT ON employeeTable TO cameron;
GRANT DELETE ON employeeTable TO cameron;
GRANT CREATE SESSION TO cameron;

--create employee table
CREATE TABLE employeeTable(
employeeId INT,
userName VARCHAR(50),
userPassword VARCHAR(50),
CONSTRAINT PK_employeeId PRIMARY KEY (employeeId)
)

--create info table
CREATE TABLE infoTable(
employeeId INT,
firstName VARCHAR(50) NOT NULL,
middleName VARCHAR(1),
lastName VARCHAR(50) NOT NULL,
CONSTRAINT fk_employeeId FOREIGN KEY (employeeId)
REFERENCES employeeTable(employeeId)
)

--create reimbursement table
CREATE TABLE reimbursementTable (
reId INT,
requesterId INT NOT NULL,
approverId INT,
categoryName VARCHAR(50),
status VARCHAR(50),
CONSTRAINT fk_requester FOREIGN KEY (requesterId)
REFERENCES employeeTable(employeeId),
CONSTRAINT fk_approverId FOREIGN KEY (approverId)
REFERENCES  employeeTable(employeeId),
CONSTRAINT PK_reId PRIMARY KEY (reId)
)

