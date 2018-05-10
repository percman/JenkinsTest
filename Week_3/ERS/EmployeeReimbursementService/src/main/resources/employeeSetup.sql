CREATE TABLE employee(
emp_username VARCHAR2(30),
emp_password VARCHAR2(100),
emp_id     NUMBER,
CONSTRAINT uk_emp_username UNIQUE (emp_username),
CONSTRAINT pk_emp_id PRIMARY KEY(emp_id));

CREATE TABLE generic_employee(
emp_first_name VARCHAR2(30),
emp_last_name VARCHAR2(30),
gen_emp_id NUMBER,
CONSTRAINT fk_generic_id FOREIGN KEY(gen_emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
CONSTRAINT pk_generic_id PRIMARY KEY(gen_emp_id));

CREATE TABLE finance_manager(
man_first_name VARCHAR2(30),
man_last_name VARCHAR2(30),
man_id NUMBER,
CONSTRAINT FK_id FOREIGN KEY(man_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
CONSTRAINT pk_manager_id PRIMARY KEY(man_id));


CREATE TABLE reimbursement(
rebur_id NUMBER,
category VARCHAR2(20),
approved NUMBER(1) default 0,
approver_id NUMBER,
submitter_id NUMBER,
CONSTRAINT ckUserApproved check (approved in (0,1,-1)),
CONSTRAINT FK_submit FOREIGN KEY(submitter_id) REFERENCES generic_employee(gen_emp_id) ON DELETE CASCADE,
CONSTRAINT FK_approve FOREIGN KEY(approver_id) REFERENCES finance_manager(man_id) ON DELETE CASCADE,
CONSTRAINT pk_rebur_id PRIMARY KEY(rebur_id));

CREATE OR REPLACE PROCEDURE insert_employee (new_name IN VARCHAR2, new_pass IN VARCHAR2)
AS
BEGIN
    INSERT INTO EMPLOYEE(emp_username,emp_password,emp_id) VALUES(new_name,new_pass,NULL);
END;
/

CREATE OR REPLACE PROCEDURE insert_gen_employee (new_Fname IN VARCHAR2, new_Lname IN VARCHAR2,new_id IN NUMBER)
AS
BEGIN
    Delete from generic_employee where gen_emp_id = new_id;
    INSERT INTO GENERIC_EMPLOYEE VALUES(new_Fname,new_Lname,new_id);
END;
/

CREATE OR REPLACE PROCEDURE insert_manager (new_Fname IN VARCHAR2, new_Lname IN VARCHAR2,new_id IN NUMBER)
AS
BEGIN
    Delete from generic_employee where gen_emp_id = new_id;
    INSERT INTO finance_manager VALUES(new_Fname,new_Lname,new_id);
END;
/

CREATE SEQUENCE emp_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;   

CREATE OR REPLACE TRIGGER emp_insert
BEFORE INSERT 
ON employee
FOR EACH ROW 
BEGIN
    IF :new.emp_id IS NULL THEN
    SELECT emp_id_sequence.nextval INTO : new.emp_id FROM dual;
    END IF;
    Update EMPLOYEE SET EMP_PASSWORD = get_emp_hash(:new.emp_username,:new.emp_password) where EMP_USERNAME = :new.emp_username;
END;
/

CREATE OR REPLACE TRIGGER gen_emp_insert
After INSERT 
ON employee
FOR EACH ROW 
BEGIN
    insert_gen_employee('','',:new.emp_id);
END;
/

CREATE OR REPLACE FUNCTION GET_EMP_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/


CREATE OR REPLACE PROCEDURE insert_reimbursement (new_category IN VARCHAR2, new_submitter_id IN NUMBER)
AS
BEGIN
    INSERT INTO reimbursement(category,SUBMITTER_ID) VALUES(new_category,new_submitter_id);
END;
/

CREATE OR REPLACE PROCEDURE update_reimbursement(new_approver_id IN NUMBER,new_rebur_id IN NUMBER)
AS
BEGIN
    UPDATE reimbursement SET approver_id = new_approver_id WHERE REBUR_ID = new_rebur_id;
END;
/
    
CREATE SEQUENCE rebur_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;  

CREATE OR REPLACE TRIGGER rebur_insert
BEFORE INSERT 
ON reimbursement
FOR EACH ROW 
BEGIN
    IF :new.rebur_id IS NULL THEN
    SELECT rebur_id_sequence.nextval INTO : new.rebur_id FROM dual;
    END IF;
END;
/



Begin
insert_employee('max','password');
end;
/
commit;
delete from EMPLOYEE where emp_id = 10 or emp_id = 13;
select * from GENERIC_EMPLOYEE;