-- create an admin for the database 
CREATE USER adminew IDENTIFIED BY password;

-- grant the appropiate privilleges 
GRANT CONNECT TO adminew; 
GRANT CREATE ANY TABLE TO adminew; 
GRANT INSERT ANY TABLE TO adminew;
GRANT UPDATE ANY TABLE TO adminew;
GRANT DELETE ANY TABLE TO adminew;
GRANT CREATE ANY PROCEDURE TO adminew;
GRANT CREATE ANY TRIGGER TO adminew;
GRANT CREATE ANY SEQUENCE TO adminew;
GRANT UNLIMITED TABLESPACE TO adminew;

-- create a user table 
CREATE TABLE usertable (
id NUMBER(10),
username VARCHAR2(30),
password VARCHAR2(100),
lockstatus NUMBER(1),
balance NUMBER(15,2),
adminstatus NUMBER(1),
CONSTRAINT PK_USER PRIMARY KEY (id),
CONSTRAINT UK_USERNAME UNIQUE (username),
CONSTRAINT BOOLEANLOCK CHECK (lockstatus BETWEEN 0 AND 1),
CONSTRAINT BOOLEANADMIN CHECK (adminstatus BETWEEN 0 AND 1)
);

-- create a sequence which will automatically increment the primary key
CREATE SEQUENCE user_id_sequence 
    MINVALUE 0 
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- hashing function that combines username, password, and a special word 
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

-- create a before insert trigger that will autoincrement the PK and hash the password 
CREATE OR REPLACE TRIGGER user_b_insert 
BEFORE INSERT 
ON usertable
FOR EACH ROW 
BEGIN 
    IF :new.id IS NULL THEN 
        SELECT user_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

-- procedure to insert new users 
CREATE OR REPLACE PROCEDURE insert_user(new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                        new_balance IN NUMBER)
AS 
BEGIN
    INSERT INTO usertable (username, password, lockstatus, adminstatus, balance, id)
    VALUES (new_username, new_password, 1, 0, new_balance, null);
    COMMIT;
END;
/

-- procedure to insert new admins 
CREATE OR REPLACE PROCEDURE insert_admin(new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                        new_balance IN NUMBER)
AS 
BEGIN
    INSERT INTO usertable (username, password, lockstatus, adminstatus, balance, id)
    VALUES (new_username, new_password, 0, 1, new_balance, null);
    COMMIT;
END;
/

-- update user procedure
CREATE OR REPLACE PROCEDURE update_user(new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                        new_lock IN NUMBER, new_admin IN NUMBER,
                                        new_balance IN NUMBER)
AS 
BEGIN
    UPDATE usertable SET username = new_username, password = new_password, 
    lockstatus = new_lock, balance = new_balance, adminstatus = new_admin
    WHERE username = new_username;
    COMMIT;
END;
/

select * from usertable;
UPDATE_USER('

-- get balance UDF 
CREATE OR REPLACE FUNCTION get_balance(curruser VARCHAR2, currpass VARCHAR2) RETURN NUMBER
IS
   current_balance NUMBER;
   
   CURSOR cb IS
   SELECT balance
     FROM usertable
     WHERE username = curruser AND password = currpass;
BEGIN
   OPEN cb;
   FETCH cb INTO current_balance;
   IF cb%NOTFOUND THEN
      current_balance := 0;
   END IF;
   CLOSE cb;
RETURN current_balance;
END;
/

-- get lock status UDF 
CREATE OR REPLACE FUNCTION get_lockstatus(curruser VARCHAR2, currpass VARCHAR2) RETURN NUMBER
IS
   current_lock NUMBER;
   
   CURSOR cl IS
   SELECT lockstatus
     FROM usertable
     WHERE username = curruser AND password = currpass;
BEGIN
   OPEN cl;
   FETCH cl INTO current_lock;
   IF cl%NOTFOUND THEN
      current_lock := 1;
   END IF;
   CLOSE cl;
RETURN current_lock;
END;
/


-- get admin status UDF 
CREATE OR REPLACE FUNCTION get_adminstatus(curruser VARCHAR2, currpass VARCHAR2) RETURN NUMBER
IS
   current_admin NUMBER;
   
   CURSOR ca IS
   SELECT adminstatus
     FROM usertable
     WHERE username = curruser AND password = currpass;
BEGIN
   OPEN ca;
   FETCH ca INTO current_admin;
   IF ca%NOTFOUND THEN
      current_admin := 1;
   END IF;
   CLOSE ca;
RETURN current_admin;
END;
/

-- need one more sequence 


-- stores all logon and logoff attempts by the admin
--CREATE TABLE userrecord (
--logon_logoff NUMBER(1),
--time TIMESTAMP,
--CONSTRAINT PK_RECORD PRIMARY KEY (time),
--CONSTRAINT LOGONBOOL CHECK (logon_logoff BETWEEN 0 AND 1)
--);

-- junction table between usertable and userrecord
--CREATE TABLE usertable_userrecord(
--user_id NUMBER(10),
--timelog TIMESTAMP,
--CONSTRAINT UTUR_JUNCTION_TABLE PRIMARY KEY (user_id, timelog), 
--CONSTRAINT FK_TIME FOREIGN KEY (timelog) REFERENCES userrecord (time) ON DELETE CASCADE, 
--CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES usertable (id) ON DELETE CASCADE
--);

-- finish this 
-- trigger to log the logon 
--CREATE OR REPLACE TRIGGER user_logon 
--AFTER LOGON 
--ON ProjectZero
--BEGIN 
--   INSERT INTO userrecord (id, timelog) VALUES (USER, CURRENT_TIMESTAMP);
--END;
--/


select * from usertable;



