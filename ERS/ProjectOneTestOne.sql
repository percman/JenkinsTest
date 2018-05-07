-- create an admin for the database 
CREATE USER projectoneadminone IDENTIFIED BY password;

-- grant the appropiate privilleges 
GRANT CONNECT TO projectoneadminone; 
GRANT CREATE ANY TABLE TO projectoneadminone; 
GRANT INSERT ANY TABLE TO projectoneadminone;
GRANT UPDATE ANY TABLE TO projectoneadminone;
GRANT DELETE ANY TABLE TO projectoneadminone;
GRANT CREATE ANY PROCEDURE TO projectoneadminone;
GRANT CREATE ANY TRIGGER TO projectoneadminone;
GRANT CREATE ANY SEQUENCE TO projectoneadminone;
GRANT UNLIMITED TABLESPACE TO projectoneadminone;
GRANT ALTER ANY TABLE TO projectoneadminone;




-- ******************************* TABLES *******************************

-- create an employee user table 
CREATE TABLE employee (
id NUMBER(10),
username VARCHAR2(30),
password VARCHAR2(100),
manager NUMBER(1),
CONSTRAINT PK_EMPLOYEE PRIMARY KEY (id),
CONSTRAINT UK_USERNAME UNIQUE (username),
CONSTRAINT BOOLEANMANAGER CHECK (manager BETWEEN 0 AND 1)
);


-- create a personal information table 
CREATE TABLE personalinfo (
id NUMBER(10),
firstname VARCHAR2(50),
lastname VARCHAR2(50),
datehired TIMESTAMP,
email VARCHAR2(100),
phonenumber NUMBER(10),
CONSTRAINT PK_PERSONALINFO PRIMARY KEY (id),
CONSTRAINT FK_PERSONAL_EMPLOYEE FOREIGN KEY (id) REFERENCES employee (id) ON DELETE CASCADE,
CONSTRAINT CK_PHONE CHECK (phonenumber BETWEEN 1000000000 AND 9999999909)
);


-- create a reimbursement information table 
CREATE TABLE reimbursement (
id NUMBER(10),
requestor_id NUMBER(10) NOT NULL,
approver_id NUMBER(10),
category VARCHAR2(15),
status NUMBER(1),
timemade TIMESTAMP NOT NULL,
timeapproved TIMESTAMP,
CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY (id),
CONSTRAINT FK_REIMBURSEMENT_R_EMPLOYEE FOREIGN KEY (requestor_id) REFERENCES employee (id) ON DELETE CASCADE,
CONSTRAINT FK_REIMBURSEMENT_A_EMPLOYEE FOREIGN KEY (approver_id) REFERENCES employee (id) ON DELETE CASCADE,
CONSTRAINT CK_R_A CHECK (requestor_id <> approver_id), 
CONSTRAINT BOOLEANSTATUS CHECK (status BETWEEN 0 AND 1)
);






-- ******************************* SEQUENCES *******************************


CREATE SEQUENCE employee_id_sequence 
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE personalinfo_id_sequence 
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE reimbursement_id_sequence 
    START WITH 1
    INCREMENT BY 1
    NOCACHE;






-- ******************************* UDF'S *******************************


-- hashing function that combines username, password, and a special word 
CREATE OR REPLACE FUNCTION GET_EMPLOYEE_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/







-- ******************************* TRIGGERS *******************************

-- create a before-insert-into-employee trigger that will autoincrement the PK and hash the password 
CREATE OR REPLACE TRIGGER employee_b_insert 
BEFORE INSERT 
ON employee
FOR EACH ROW 
BEGIN 
    IF :new.id IS NULL THEN 
        SELECT employee_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
    SELECT GET_EMPLOYEE_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

-- create a before-insert-into-personalinfo trigger that will autoincrement the PK and hash the password 
CREATE OR REPLACE TRIGGER personalinfo_b_insert 
BEFORE INSERT 
ON personalinfo
FOR EACH ROW 
BEGIN 
    IF :new.id IS NULL THEN 
        SELECT personalinfo_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
END;
/


-- create a before-insert-into-reimbursement trigger that will autoincrement the PK
CREATE OR REPLACE TRIGGER reimbursement_b_insert 
BEFORE INSERT 
ON reimbursement
FOR EACH ROW 
BEGIN 
    IF :new.id IS NULL THEN 
        SELECT reimbursement_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
END;
/








-- ******************************* PROCEDURES *******************************


-- procedure to insert new employees or managers into employee and personalinfo 
-- manager role is specified by the JDBC
CREATE OR REPLACE PROCEDURE insert_employee(new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                        new_manager IN NUMBER,
                                        new_firstname IN VARCHAR2, new_lastname IN VARCHAR2,
                                        new_email IN VARCHAR2, new_phonenumber IN NUMBER)
AS 
BEGIN
    INSERT INTO employee (username, password, manager, id)
    VALUES (new_username, new_password, new_manager, null);
    COMMIT;
    INSERT INTO personalinfo(firstname, lastname, datehired, email, phonenumber, id)
    VALUES (new_firstname, new_lastname, CURRENT_TIMESTAMP, new_email, new_phonenumber, null);
    COMMIT;
END;
/



-- procedure to promote an employee
CREATE OR REPLACE PROCEDURE promote_employee(same_username IN VARCHAR2)
AS
BEGIN
    UPDATE employee SET manager = 1 WHERE username = same_username;
    COMMIT;
END;
/

-- procedure to update an employee's personal information 
CREATE OR REPLACE PROCEDURE update_personalinfo(same_id IN NUMBER, 
                                        new_firstname IN VARCHAR2, new_lastname IN VARCHAR2,
                                        new_email IN VARCHAR2, new_phonenumber IN NUMBER)
AS 
BEGIN
    UPDATE personalinfo SET firstname = new_firstname, lastname = new_lastname,
    email = new_email, phonenumber = new_phonenumber
    WHERE id = same_id;
    COMMIT;
END;
/


-- procedure to insert a reimbursement
CREATE OR REPLACE PROCEDURE insert_reimbursement(new_requestor_id IN NUMBER, new_category in VARCHAR2)
AS 
BEGIN
    INSERT INTO reimbursement (requestor_id, category, status, timemade, id)
    VALUES (new_requestor_id, new_category, 0, CURRENT_TIMESTAMP, null);
    COMMIT;
END;
/

-- procedure to approve a reimbursement
CREATE OR REPLACE PROCEDURE update_reimbursement(same_id IN NUMBER, new_approver_id IN NUMBER, new_category in VARCHAR2)
AS 
BEGIN
    UPDATE reimbursement SET approver_id = new_approver_id, status = 1, timeapproved = CURRENT_TIMESTAMP
    WHERE id = same_id;
    COMMIT;
END;
/






