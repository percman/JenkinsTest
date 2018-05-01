CREATE USER admin IDENTIFIED BY adminpassword;

GRANT CONNECT TO admin; 
GRANT CREATE ANY TABLE TO admin; 
GRANT INSERT ANY TABLE TO admin;
GRANT UPDATE ANY TABLE TO admin;
GRANT DELETE ANY TABLE TO admin;
GRANT CREATE ANY PROCEDURE TO admin;
GRANT CREATE ANY TRIGGER TO admin;
GRANT CREATE ANY SEQUENCE TO admin;
GRANT UNLIMITED TABLESPACE TO admin;
GRANT ALTER ANY TABLE TO admin;

CREATE TABLE bank_user (
id NUMBER(6),
username VARCHAR2(30 CHAR),
password VARCHAR2(100 CHAR),
typeid NUMBER(1),
CONSTRAINT PK_BANK_USER PRIMARY KEY(id), 
CONSTRAINT UK_USERNAME UNIQUE(username)
);

CREATE TABLE admin (
username VARCHAR2(30 CHAR),
password VARCHAR2(100 CHAR),
CONSTRAINT PK_ADMIN PRIMARY KEY (username)
);  

CREATE TABLE customer (
username VARCHAR2 (30 CHAR),
password VARCHAR2 (100 CHAR),
approvalcode NUMBER (1),
lockcode NUMBER (1),
rejected NUMBER (1), 
accountbalance NUMBER(11, 2),
CONSTRAINT PK_CUSTOMER PRIMARY KEY (username)
);



CREATE SEQUENCE customer_id_sequence
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0 
    MAXVALUE 2000
    NOCACHE;
    
CREATE SEQUENCE admin_id_sequence
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 10
    NOCACHE;
    
    
CREATE SEQUENCE bank_user_id_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 5000
    NOCACHE;
    
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/
    
CREATE OR REPLACE TRIGGER bank_user_insert
    BEFORE INSERT
    ON bank_user
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN 
            SELECT bank_user_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

CREATE OR REPLACE PROCEDURE insert_user(new_username IN VARCHAR2, new_password IN VARCHAR2, new_typeid IN NUMBER)
AS 
BEGIN
    INSERT INTO bank_user (username, password, typeid, id)
    VALUES(new_username, new_password, new_typeid, null);
    COMMIT;
END;
/


CREATE OR REPLACE PROCEDURE insert_customer(new_username IN VARCHAR2, new_password IN VARCHAR2, new_approvalcode IN NUMBER, new_lockcode IN NUMBER, new_rejected IN NUMBER, new_accountbalance IN NUMBER)
AS 
BEGIN
    INSERT INTO customer (username, password, approvalcode, lockcode, rejected, accountbalance)
    VALUES(new_username, new_password, new_approvalcode, new_lockcode, new_rejected, new_accountbalance);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE update_customer(new_username IN VARCHAR2, new_approvalcode IN NUMBER, new_lockcode IN NUMBER, new_rejected IN NUMBER, new_accountbalance IN NUMBER)
AS
BEGIN
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE customer
    SET accountbalance = new_accountbalance, approvalcode = new_approvalcode, lockcode = new_lockcode, rejected = new_rejected
    WHERE username = new_username;
    COMMIT;
END;
/
    
CREATE OR REPLACE PROCEDURE insert_admin(new_username IN VARCHAR2, new_password IN VARCHAR2)
AS
BEGIN
    INSERT INTO admin (username, password)
    VALUES (new_username, new_password);
    COMMIT;
END;
/


CREATE OR REPLACE TRIGGER customer_insert
    BEFORE INSERT
    ON customer
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN 
            SELECT customer_id_sequence.nextval INTO :new.id FROM dual;
            END IF;
    END;
    /
    
CREATE OR REPLACE TRIGGER admin_insert
    BEFORE INSERT
    ON Admin
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN 
            SELECT admin_id_sequence.nextval INTO :new.id FROM dual;
            END IF;
    END;
    /
  
CREATE OR REPLACE FUNCTION get_user_type(get_id NUMBER) RETURN NUMBER
AS 
    user_type NUMBER;
    CURSOR A IS
    SELECT id FROM bank_user WHERE id = get_id;
BEGIN
    OPEN A;
    FETCH A INTO user_type; 
    CLOSE A;
    RETURN user_type;
END;
/



CREATE OR REPLACE FUNCTION get_current_time RETURN TIMESTAMP
AS
    time_right_now TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
    dbms_output.put_line('The current time is ' || time_right_now);
    return time_right_now;
END;
/



INSERT INTO bank_user VALUES (1, 'Art', 'adminpassword', 1);
INSERT INTO bank_user VALUES (2, 'Bob', 'password', 2);
INSERT INTO bank_user VALUES (3, 'Chris', 'password', 2);
INSERT INTO bank_user VALUES (4, 'Dean', 'password', 2);

INSERT INTO admin VALUES ('Art', 'adminpassword');
INSERT INTO customer VALUES ('Bob', 'password', 0, 0, 0, 2000);
INSERT INTO customer VALUES ('Chris', 'password', 0, 0, 0, 0);
INSERT INTO bank_user VALUES ('Dean', 'password', 1, 0, 0, 1000);