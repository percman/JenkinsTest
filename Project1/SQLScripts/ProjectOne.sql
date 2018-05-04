--Create an admins
CREATE USER cameronskaggs identified by mypassword;
--Grant privileges
GRANT INSERT ON employeeTable TO cameron;
GRANT UPDATE ON employeeTable TO cameron;
GRANT SELECT ON employeeTable TO cameron;
GRANT DELETE ON employeeTable TO cameron;
GRANT CREATE SESSION TO cameron;

GRANT INSERT ON infoTable TO cameron;
GRANT UPDATE ON infoTable TO cameron;
GRANT SELECT ON infoTable TO cameron;
GRANT DELETE ON infoTable TO cameron;
GRANT CREATE SESSION TO cameron;

CREATE TABLE employeeTable(
employeeId INT,
userName VARCHAR(50),
userPassword VARCHAR(50),
CONSTRAINT PK_employeeId PRIMARY KEY (employeeId)
)
INSERT INTO infoTable VALUES (1, 'Cameron', 'J', 'Skaggs');

SELECT * FROM EMPLOYEETABLE, INFOTABLE where EMPLOYEETABLE.EMPLOYEEID = INFOTABLE.EMPLOYEEID;
COMMIT;
--create info table
CREATE TABLE infoTable(
employeeId INT,
firstName VARCHAR(50) NOT NULL,
middleName VARCHAR(1),
lastName VARCHAR(50) NOT NULL,
CONSTRAINT fk_employeeId FOREIGN KEY (employeeId)
REFERENCES employeeTable(employeeId)
)

ALTER TABLE INFOTABLE MODIFY middleName VARCHAR(1);
COMMIT;
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
--create procedure to insert users
--Creating a stored procudure to insert users
CREATE OR REPLACE PROCEDURE insert_employee(u_name IN VARCHAR, u_password IN VARCHAR, f_name IN VARCHAR, m_name IN VARCHAR, l_name IN VARCHAR)
AS
BEGIN
    --INSERT INTO employee
    INSERT INTO employeeTable (username, userpassword) 
    VALUES (u_name, u_password);
    INSERT INTO infoTable (firstName, middleName, lastName)
    VALUES (f_name, m_name, l_name);
END;
/
--A hashing function for passwords
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR, PASSWORD VARCHAR) RETURN VARCHAR
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/
COMMIT;
--Employee Sequence for iterating through id
CREATE SEQUENCE employee_sequence
    START WITH 1
    MINVALUE 0
    NOCACHE;

CREATE OR REPLACE TRIGGER employee_before_insert
    BEFORE INSERT
    ON employeeTable
    FOR EACH ROW
    BEGIN
        IF: new.employeeId IS NULL THEN
        SELECT employee_sequence.nextval INTO :new.employeeId FROM dual;
        END IF;
    END;
    /

--Info Sequence for iterating through id
CREATE SEQUENCE info_sequence
    START WITH 1
    MINVALUE 0
    NOCACHE;

CREATE OR REPLACE TRIGGER info_before_insert
    BEFORE INSERT
    ON infoTable
    FOR EACH ROW
    BEGIN
        IF: new.employeeId IS NULL THEN
        SELECT info_sequence.nextval INTO :new.employeeId FROM dual;
        END IF;
    END;
    /
    
INSERT INTO INFOTABLE VALUES (null, 'Cameron', 'J', 'Skaggs');
DELETE FROM INFOTABLE;
DELETE FROM EMPLOYEETABLE;
SELECT * FROM INFOTABLE;
INSERT INTO EMPLOYEETABLE VALUES (null, 'andrew6', 'password1');
DELETE FROM EMPLOYEETABLE e where e.employeeId = 2;
DELETE FROM INFOTABLE;
SELECT * FROM EMPLOYEETABLE;
SELECT * FROM INFOTABLE;
COMMIT;


SELECT * FROM EMPLOYEETABLE, INFOTABLE; 