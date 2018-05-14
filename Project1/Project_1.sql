--CREATE USER project_1 IDENTIFIED BY reimburse;
--
--GRANT CONNECT TO project_1;
--GRANT CREATE ANY TABLE TO project_1;
--GRANT UPDATE ANY TABLE TO project_1;
--GRANT DELETE ANY TABLE TO project_1;
--GRANT INSERT ANY TABLE TO project_1;
--GRANT CREATE ANY PROCEDURE TO project_1;
--GRANT CREATE ANY TRIGGER TO project_1;
--GRANT CREATE ANY SEQUENCE TO project_1;
--GRANT UNLIMITED TABLESPACE TO project_1;

CREATE TABLE employee(
    employee_id NUMBER(10),
    username VARCHAR2(20) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY (employee_id),
    CONSTRAINT EMPLOYEE_UNIQUE UNIQUE (username)
    );

CREATE TABLE info(
    info_id NUMBER(10),
    employee_id NUMBER(10) NOT NULL,
    first_name VARCHAR2(20) NOT NULL,
    middle_initial CHAR,
    last_name VARCHAR2(20) NOT NULL,
    CONSTRAINT INFO_PK PRIMARY KEY (info_id),
    CONSTRAINT INFO_FK FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    CONSTRAINT INFO_UNIQUE UNIQUE (employee_id)
    );
    
CREATE TABLE manager(
    manager_id NUMBER(10),
    employee_id NUMBER(10),
    CONSTRAINT MANAGER_PK PRIMARY KEY (manager_id),
    CONSTRAINT MANAGER_FK FOREIGN KEY(employee_id) REFERENCES employee(employee_id)
);
    
CREATE TABLE reimbursement(
    reimbursement_id NUMBER(10),
    requestor_id NUMBER(10) NOT NULL,
    approver_id NUMBER(10),
    category VARCHAR2(10) NOT NULL,
    status VARCHAR2(10) DEFAULT 'Pending',
    CONSTRAINT REIMBURSEMENT_PK PRIMARY KEY (reimbursement_id),
    CONSTRAINT REQUESTOR_FK FOREIGN KEY (requestor_id) REFERENCES employee(employee_id),
    CONSTRAINT APPROVER_FK FOREIGN KEY (approver_id) REFERENCES manager(manager_id),
    CONSTRAINT CATEGORY_CHECK CHECK(category IN('Lodging', 'Travel', 'Food', 'Other')),
    CONSTRAINT STATUS_CHECK CHECK(status IN('Pending', 'Approved', 'Denied'))
    );
    
ALTER TABLE reimbursement
ADD (amount NUMBER(10, 2),
request_time TIMESTAMP,
approval_time TIMESTAMP);

ALTER TABLE reimbursement
ADD image VARCHAR2(20);

ALTER TABLE reimbursement MODIFY image VARCHAR2(50);

CREATE SEQUENCE employee_id_sequence
START WITH 1
INCREMENT BY 1
NOCACHE;
    
CREATE SEQUENCE manager_id_sequence
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE info_id_sequence
START WITH 1
INCREMENT BY 1
NOCACHE;
    
CREATE SEQUENCE reimbursement_id_sequence
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

CREATE OR REPLACE TRIGGER employee_insert
BEFORE insert
ON employee
FOR EACH ROW
BEGIN
    IF :new.employee_id IS NULL THEN
        SELECT employee_id_sequence.nextval INTO :new.employee_id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

CREATE OR REPLACE TRIGGER info_insert
BEFORE insert
ON info
FOR EACH ROW
BEGIN
    IF :new.info_id IS NULL THEN
        SELECT info_id_sequence.nextval INTO :new.info_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER manager_insert
BEFORE insert
ON manager
FOR EACH ROW
BEGIN
    IF :new.manager_id IS NULL THEN
        SELECT manager_id_sequence.nextval INTO :new.manager_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reimbursement_insert
BEFORE insert
ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.reimbursement_id IS NULL THEN
        SELECT reimbursement_id_sequence.nextval INTO :new.reimbursement_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insert_employee(new_username IN VARCHAR2, new_password IN VARCHAR2, new_fname IN VARCHAR2, 
    new_minit IN CHAR, new_lname IN VARCHAR2)
AS
    emp_id NUMBER(10);
BEGIN
    INSERT INTO employee(username, password)
    VALUES (new_username, new_password);
    
    SELECT employee_id INTO emp_id FROM employee WHERE username=new_username;
    
    INSERT INTO info(employee_id, first_name, middle_initial, last_name)
    VALUES(emp_id, new_fname, new_minit, new_lname);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insert_manager(new_username IN VARCHAR2, new_password IN VARCHAR2, new_fname IN VARCHAR2, 
    new_minit IN CHAR, new_lname IN VARCHAR2)
AS
    emp_id NUMBER(10);
BEGIN
    INSERT INTO employee(username, password)
    VALUES (new_username, new_password);
    
    SELECT employee_id INTO emp_id FROM employee WHERE username=new_username;
    
    INSERT INTO info(employee_id, first_name, middle_initial, last_name)
    VALUES(emp_id, new_fname, new_minit, new_lname);
    
    INSERT INTO manager(employee_id)
    VALUES(emp_id);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insert_reimbursement(requestor IN NUMBER, category_name IN VARCHAR2, amt IN NUMBER, img IN VARCHAR2)
AS
BEGIN
    INSERT INTO reimbursement(requestor_id, category, request_time, amount, image)
    VALUES(requestor, category_name, CURRENT_TIMESTAMP, amt, img);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE approve_deny(response IN VARCHAR2, reimburseid IN VARCHAR2, manage_id IN NUMBER)
AS
BEGIN
    UPDATE reimbursement SET status=response, approver_id=manage_id, approval_time=CURRENT_TIMESTAMP
    WHERE reimbursement_id=reimburseid;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE update_employee(id IN NUMBER, user_name IN VARCHAR2, pass_word IN VARCHAR2, firstname IN VARCHAR2,
    middleinit IN CHAR, lastname IN VARCHAR2)
AS
BEGIN
    UPDATE employee SET username=user_name, password=GET_USER_HASH(user_name, pass_word) WHERE employee_id=id;
    UPDATE info SET first_name=firstname, middle_initial=middleinit, last_name=lastname WHERE employee_id=id;
    COMMIT;
END;
/