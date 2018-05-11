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

--Update emoplymentSELECT DISTINCT billingstate FROM invoice;

CREATE OR REPLACE PROCEDURE update_employee(f_name IN VARCHAR, l_name IN VARCHAR, u_name IN VARCHAR, u_password IN VARCHAR)
AS
e_id number;
h_password varchar(50);
cursor c1 is SELECT employeeid FROM EMPLOYEETABLE e WHERE e.userName = u_name;

BEGIN
    open c1;
    fetch c1 into e_id;
    CLOSE c1;
    
    h_password := get_user_hash(u_name, u_password);
    
    UPDATE employeeTable SET 
    userPassword = h_password 
    WHERE username = u_name;

    UPDATE infotable SET
    firstName = f_name,
    lastName = l_name
    WHERE employeeid = e_id;
END;
/
select * from infotable;

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
-- trigger for hashing password and incrementing employee id
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
CREATE OR REPLACE TRIGGER employee_before_update
    BEFORE INSERT
    ON employeeTable
    FOR EACH ROW
    BEGIN
        SELECT GET_USER_HASH(:new.userName, :new.userPassword)
        INTO :new.userPassword FROM dual;
    END;
    /
    
SELECT * FROM EMPLOYEETABLE  OUTER JOIN INFOTABLE ON employeetable.EMPLOYEEID = infotable.employeeid;

SELECT * FROM employeetable e,infotable i where e.employeeid = i.employeeid;
commit;

CREATE OR REPLACE TRIGGER reimbursement_before_insert
    BEFORE INSERT
    ON reimbursementTable
    FOR EACH ROW
    BEGIN
        IF: new.reid IS NULL THEN
        SELECT reimbursement_sequence.nextval INTO :new.reid FROM dual;
        END IF;
        
    END;
    /
-- insert date submitted
CREATE OR REPLACE TRIGGER reimbursement_date_insert
    BEFORE INSERT
    ON reimbursementTable
    FOR EACH ROW
    BEGIN
        IF: new.dateSubmitted IS NULL THEN
        select CURRENT_DATE INTO :new.dateSubmitted FROM dual;
        END IF;
        
    END;
    /
    
select employee_sequence.currval from dual;

CREATE OR REPLACE PROCEDURE make_request(rq_Id IN INT, c_name IN VARCHAR, n_status IN INT, n_amount IN INT)
AS
BEGIN

    INSERT INTO reimbursementTable(reId, requesterId, categoryName, status, amount) 
    VALUES(rq_Id, c_name, n_status, n_amount);
END;
/

CREATE SEQUENCE reimbursement_sequence
    START WITH 1
    MINVALUE 0
    NOCACHE;
select * from reimbursementTable;

ALTER TABLE reimbursementTable add dateCompleted DATE;
select * from employeeTable;

CREATE OR REPLACE PROCEDURE update_withoutpassword(f_name IN VARCHAR, l_name IN VARCHAR, u_name IN VARCHAR)
AS
e_id number;
h_password varchar(50);
cursor c1 is SELECT employeeid FROM EMPLOYEETABLE e WHERE e.userName = u_name;

BEGIN
    open c1;
    fetch c1 into e_id;
    CLOSE c1;

    UPDATE infotable SET
    firstName = f_name,
    lastName = l_name
    WHERE employeeid = e_id;
END;
/