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
    message VARCHAR2(100),
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

--Receipt Table
CREATE TABLE receipt (
    receipt_id NUMBER (10) UNIQUE,
    receipt BLOB,
    CONSTRAINT PK_RECEIPT FOREIGN KEY (receipt_id)
    REFERENCES reimbursement(reimbursement_id)
);

SELECT * FROM employee;
SELECT * FROM financial_manager;
SELECT * FROM reimbursement;
SELECT * FROM employee_information;
SELECT * FROM receipt;

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

--Trigger to increment reimbursement id
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

--Trigger to get proper value into manager id
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

--Trigger to get proper value into employee info employee id
CREATE OR REPLACE TRIGGER employee_info_b_insert
BEFORE INSERT
ON employee_information
FOR EACH ROW
BEGIN
    IF :new.employee_id IS NULL THEN
        SELECT employee_id_sequence.currval INTO :new.employee_id FROM dual;
    END IF;
END;
/

--Trigger to get proper value into receipt for request id
CREATE OR REPLACE TRIGGER receipt_b_insert
BEFORE INSERT
ON receipt
FOR EACH ROW
BEGIN
    IF :new.receipt_id IS NULL THEN
        SELECT reimbursement_id_sequence.currval INTO :new.receipt_id FROM dual;
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

--Create a stored procedure to insert new employees
CREATE OR REPLACE PROCEDURE insert_employee(new_username IN VARCHAR2, new_password IN VARCHAR2, new_firstname IN VARCHAR2, new_lastname IN VARCHAR2)
AS
BEGIN
    INSERT INTO employee (employee_id, username, password)
    VALUES (null, new_username, new_password);
    INSERT INTO employee_information (employee_id, firstname, lastname)
    VALUES (null, new_firstname, new_lastname);
    COMMIT;
END;
/

--Create a stored procedure to modify an existing employee
CREATE OR REPLACE PROCEDURE modify_employee(new_employee_id IN NUMBER, new_username IN VARCHAR2, new_firstname IN VARCHAR2, new_lastname IN VARCHAR2)
AS
BEGIN
    UPDATE employee SET username = new_username WHERE employee_id = new_employee_id;
    UPDATE employee_information SET firstname = new_firstname, lastname = new_lastname WHERE employee_id = new_employee_id;
    COMMIT;
END;
/

--Create a stored procedure to insert a new reimbursement
CREATE OR REPLACE PROCEDURE insert_reimbursement(new_r_id IN NUMBER, new_category IN NUMBER, new_amount IN NUMBER,
                                                    new_message VARCHAR2, new_status IN NUMBER, new_blob BLOB)
AS
BEGIN
    INSERT INTO reimbursement (reimbursement_id, requestor_id, f_manager_id, category, amount, status, request_date, approve_date, message)
    VALUES (null, new_r_id, null, new_category, new_amount, new_status, CURRENT_TIMESTAMP, null, new_message);
    INSERT INTO receipt (receipt_id, receipt) VALUES (null, new_blob);
    COMMIT;
END;
/

--Create a stored procedure to insert new managers
CREATE OR REPLACE PROCEDURE insert_manager(new_username IN VARCHAR2, new_password IN VARCHAR2, new_firstname IN VARCHAR2, new_lastname IN VARCHAR2)
AS
BEGIN
    INSERT INTO employee (employee_id, username, password)
    VALUES (null, new_username, new_password);
    INSERT INTO employee_information (employee_id, firstname, lastname)
    VALUES (null, new_firstname, new_lastname);
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