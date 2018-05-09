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

GRANT INSERT ON reimbursementTable TO cameron;
GRANT UPDATE ON reimbursementTable TO cameron;
GRANT SELECT ON reimbursementTable TO cameron;
GRANT DELETE ON reimbursementTable TO cameron;
GRANT CREATE SESSION TO cameron;

CREATE TABLE employeeTable(
employeeId INT,
userName VARCHAR(50),
userPassword VARCHAR(50),
CONSTRAINT PK_employeeId PRIMARY KEY (employeeId)
)
SELECT * FROM employeeTable, infoTable  WHERE EMPLOYEETABLE.employeeId = INFOTABLE.employeeId;
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


SELECT * FROM EMPLOYEETABLE e FULL OUTER JOIN INFOTABLE i ON e.employeeId = i.employeeId WHERE userName = 'nixnax';

--Update emoplyment
CREATE OR REPLACE PROCEDURE update_employee(f_name IN VARCHAR, l_name IN VARCHAR, u_name IN VARCHAR, u_password IN VARCHAR)
AS
e_id number;
cursor c1 is SELECT employeeid FROM EMPLOYEETABLE e WHERE e.userName = u_name;

BEGIN
    open c1;
    fetch c1 into e_id;
    CLOSE c1;
    
    UPDATE employeeTable SET
    userName = u_name, 
    userPassword = u_password 
    WHERE username = u_name;
    
    UPDATE INFOTABLE SET
    firstName = f_name,
    lastName = l_name,
    WHERE employeeid = e_id;
    
    COMMIT;
END;
/
commit;
--Creating a stored procudure to insert employees
CREATE OR REPLACE PROCEDURE insert_employee(u_name IN VARCHAR, u_password IN VARCHAR, 
f_name IN VARCHAR, m_name IN VARCHAR, l_name IN VARCHAR, fm IN INT)
AS
BEGIN
    --INSERT INTO employee
    INSERT INTO employeeTable (username, userpassword, FINANCEMANAGER) 
    VALUES (u_name, u_password, fm);
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
        SELECT GET_USER_HASH(:new.userName, :new.userPassword)
        INTO :new.userPassword FROM dual;
    END;
    /

SELECT * FROM EMPLOYEETABLE  OUTER JOIN INFOTABLE ON employeetable.EMPLOYEEID = infotable.employeeid;

SELECT * FROM employeetable e,infotable i where e.employeeid = i.employeeid;

CREATE OR REPLACE TRIGGER info_before_insert
    BEFORE INSERT
    ON infoTable
    FOR EACH ROW
    BEGIN
        IF: new.employeeId IS NULL THEN
        SELECT employee_sequence.currval INTO :new.employeeId FROM dual;
        END IF;
        
    END;
    /
    
select employee_sequence.currval from dual;
CREATE OR REPLACE PROCEDURE make_request(rmb_Id IN INT, rq_Id IN INT, a_Id IN INT, c_name IN VARCHAR, status IN VARCHAR)
AS
BEGIN
    --INSERT INTO employee
    INSERT INTO reimbursementTable (reId, requesterId, approverId, categoryName, status) 
    VALUES (rmb_Id, rq_Id, a_Id, c_name, status);
END;
/

select * from reimbursementTable;