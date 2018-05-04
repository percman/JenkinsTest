--Create a user for the database connection
CREATE USER db_admin2 IDENTIFIED BY db_admin2;

--Grant the appropriate privileges to the user
GRANT CONNECT TO db_admin2;
GRANT CREATE ANY TABLE TO db_admin2;
GRANT INSERT ANY TABLE TO db_admin2;
GRANT UPDATE ANY TABLE TO db_admin2;
GRANT DELETE ANY TABLE TO db_admin2;
GRANT CREATE ANY PROCEDURE TO db_admin2;
GRANT CREATE ANY TRIGGER TO db_admin2;
GRANT CREATE ANY SEQUENCE TO db_admin2;
GRANT UNLIMITED TABLESPACE TO db_admin2;

--Revoke the appropriate privileges to the user
REVOKE CONNECT FROM db_admin2;
REVOKE CREATE ANY TABLE FROM db_admin2;
REVOKE INSERT ANY TABLE FROM db_admin2;
REVOKE UPDATE ANY TABLE FROM db_admin2;
REVOKE DELETE ANY TABLE FROM db_admin2;
REVOKE CREATE ANY PROCEDURE FROM db_admin2;
REVOKE CREATE ANY TRIGGER FROM db_admin2;
REVOKE CREATE ANY SEQUENCE FROM db_admin2;
REVOKE UNLIMITED TABLESPACE FROM db_admin2;

---------------------------------------------TABLES------------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Employee Table
CREATE TABLE employee (
    employee_id NUMBER(10),
    username VARCHAR2(40) UNIQUE,
    password VARCHAR2(40),
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (employee_id)
);

--Employee Information Table
CREATE TABLE employee_information (
    employee_id NUMBER(10),
    firstname VARCHAR2(40),
    lastname VARCHAR2(40),
    CONSTRAINT FK_EMPLOYEE_INFORMATION FOREIGN KEY (employee_id)
    REFERENCES Employee(employee_id)
);

--Reimbursement Table
CREATE TABLE reimbursement (
    reimbursement_id NUMBER(10),
    requestor_id NUMBER(10),
    f_manager_id NUMBER(10),
    category NUMBER(1),
    amount NUMBER(10),
    status NUMBER(1),
    request_date TIMESTAMP,
    approve_date TIMESTAMP,
    CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY (reimbursement_id),
    CONSTRAINT FK_EMPLOYEE FOREIGN KEY (requestor_id)
    REFERENCES employee(employee_id),
    CONSTRAINT FK_FINANCIAL_MANAGER FOREIGN KEY (f_manager_id)
    REFERENCES financial_manager(f_manager_id)
);

--Manager Table
CREATE TABLE financial_manager (
    employee_id NUMBER(10) UNIQUE,
    f_manager_id NUMBER(10),
    CONSTRAINT PK_FINACIAL_MANAGER PRIMARY KEY (f_manager_id),
    CONSTRAINT FK_MANAGER_ID FOREIGN KEY (employee_id)
    REFERENCES employee(employee_id)
);

SELECT * FROM employee e 
FULL OUTER JOIN employee_information ei ON e.employee_id = ei.employee_id 
FULL OUTER JOIN financial_manager f ON e.employee_id = f.employee_id
WHERE f_manager_id > 0 ;

SELECT * FROM employee;
SELECT * FROM financial_manager;
SELECT * FROM reimbursement;
SELECT * FROM employee_information;

DELETE FROM employee;

DROP TABLE reimbursement;
DROP TABLE financial_manager;

--------------------------------------------SEQUENCES--------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Increment the employee_id
CREATE SEQUENCE employee_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
--Increment the reimbursement_id
CREATE SEQUENCE reimbursement_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
--Increment the financial_manager_id
CREATE SEQUENCE f_manager_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
---------------------------------------------TRIGGERS--------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Create a before insert trigger that will autoincrement the employee_id number
CREATE OR REPLACE TRIGGER employee_id_b_insert
BEFORE INSERT
ON employee
FOR EACH ROW
BEGIN
    IF :new.employee_id IS NULL THEN
        SELECT employee_id_sequence.nextval INTO :new.employee_id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

CREATE OR REPLACE TRIGGER reimbursement_id_b_insert
BEFORE INSERT
ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.reimbursement_id IS NULL THEN
        SELECT reimbursement_id_sequence.nextval INTO :new.reimbursement_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER f_manager_id_b_insert
BEFORE INSERT
ON financial_manager
FOR EACH ROW
BEGIN
    IF :new.f_manager_id IS NULL THEN
        SELECT f_manager_id_sequence.nextval INTO :new.f_manager_id FROM dual;
    END IF;
    IF :new.employee_id IS NULL THEN
        SELECT employee_id_sequence.currval INTO :new.employee_id FROM dual;
    END IF;
END;
/

--------------------------------------------FUNCTIONS--------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Hashing function that combines username, password and a special word
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'PENGUIN';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

-------------------------------------------PROCEDURES--------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Create a stored procedure to insert new users
CREATE OR REPLACE PROCEDURE insert_employee(new_username IN VARCHAR2, new_password IN VARCHAR2)
AS
BEGIN
    INSERT INTO employee (employee_id, username, password)
    VALUES (null, new_username, new_password);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insert_reimbursement(new_r_id IN NUMBER, new_category IN NUMBER, new_amount IN NUMBER, new_status IN NUMBER)
AS
BEGIN
    INSERT INTO reimbursement (reimbursement_id, requestor_id, f_manager_id, category, amount, status, request_date, approve_date)
    VALUES (null, new_r_id, null, new_category, new_amount, new_status, CURRENT_TIMESTAMP, null);
    COMMIT;
END;
/

--Create a stored procedure to insert new users
CREATE OR REPLACE PROCEDURE insert_manager(new_username IN VARCHAR2, new_password IN VARCHAR2)
AS
BEGIN
    INSERT INTO employee (employee_id, username, password)
    VALUES (null, new_username, new_password);
    INSERT INTO financial_manager (employee_id, f_manager_id)
    VALUES (null, null);
    COMMIT;
END;
/

--Create a stored procedure to approve reimbursements
CREATE OR REPLACE PROCEDURE approve_reimbursement(new_id NUMBER, new_f_manager_id NUMBER, new_reimbursement_id NUMBER)
AS
BEGIN
    UPDATE reimbursement SET status = 1 WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    UPDATE reimbursement SET f_manager_id = new_f_manager_id WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    UPDATE reimbursement SET approve_date = CURRENT_TIMESTAMP WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    COMMIT;
END;
/

--Create a stored procedure to approve reimbursements
CREATE OR REPLACE PROCEDURE deny_reimbursement(new_id NUMBER, new_f_manager_id NUMBER, new_reimbursement_id NUMBER)
AS
BEGIN
    UPDATE reimbursement SET status = -1 WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    UPDATE reimbursement SET f_manager_id = new_f_manager_id WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    UPDATE reimbursement SET approve_date = CURRENT_TIMESTAMP WHERE requestor_id = new_id AND reimbursement_id = new_reimbursement_id;
    COMMIT;
END;
/

COMMIT;
ROLLBACK;
SAVEPOINT;