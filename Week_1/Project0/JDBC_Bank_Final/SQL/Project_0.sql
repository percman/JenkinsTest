--Create a user for the database connection
CREATE USER db_admin IDENTIFIED BY db_admin;

--Grant the appropriate privileges to the user
GRANT CONNECT TO db_admin;
GRANT CREATE ANY TABLE TO db_admin;
GRANT INSERT ANY TABLE TO db_admin;
GRANT UPDATE ANY TABLE TO db_admin;
GRANT DELETE ANY TABLE TO db_admin;
GRANT CREATE ANY PROCEDURE TO db_admin;
GRANT CREATE ANY TRIGGER TO db_admin;
GRANT CREATE ANY SEQUENCE TO db_admin;
GRANT UNLIMITED TABLESPACE TO db_admin;

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Primary table for account information
CREATE TABLE account(
    accountnumber NUMBER(10),
    firstname VARCHAR2(40) NOT NULL,
    lastname VARCHAR2(40) NOT NULL,
    username VARCHAR2(40) NOT NULL UNIQUE,
    password VARCHAR2(40) NOT NULL,
    balance VARCHAR2(40) NOT NULL,
    approved NUMBER(1) NOT NULL,
    locked NUMBER(1) NOT NULL,
    admin NUMBER(1) NOT NULL,
    CONSTRAINT PK_ACCOUNT PRIMARY KEY (accountnumber)
);



--Table for keeping track of time stamps
CREATE TABLE user_TiCo(
    accountnumber NUMBER(10) NOT NULL,
    timestamp_id NUMBER(10),
    user_timestamp VARCHAR2(40) UNIQUE,
    CONSTRAINT FK_USER_TICO FOREIGN KEY (accountnumber)
    REFERENCES account(accountnumber)
);



--Table for keeping track of trade requests
CREATE TABLE trade_request(
    requester_accountnumber NUMBER(10) NOT NULL,
    acceptor_accountnumber NUMBER(10) NOT NULL,
    trade_amount NUMBER(10) NOT NULL,
    trade_accepted NUMBER(1) NOT NULL,
    CONSTRAINT FK_TRADE_REQUEST FOREIGN KEY (requester_accountnumber)
    REFERENCES account(accountnumber)
);

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Increment the primary key in account
CREATE SEQUENCE accountnumber_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;



--Increment the id number of each TiCo
CREATE SEQUENCE TiCo_id_sequence
    MINVALUE 0
    MAXVALUE 100000000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Create a before insert trigger that will autoincrement the PK and hash the password
CREATE OR REPLACE TRIGGER account_b_insert
BEFORE INSERT
ON account
FOR EACH ROW
BEGIN
    IF :new.accountnumber IS NULL THEN
        SELECT accountnumber_sequence.nextval INTO :new.accountnumber FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/



--Create a before insert trigger that will autoincrement the TiCo number
CREATE OR REPLACE TRIGGER TiCo_b_insert
BEFORE INSERT
ON user_TiCo
FOR EACH ROW
BEGIN
    IF :new.timestamp_id IS NULL THEN
        SELECT TiCo_id_sequence.nextval INTO :new.timestamp_id FROM dual;
    END IF;
END;
/

-------------------------------------------------------------------------------------------------------
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



--Function to count the number of pending users
CREATE OR REPLACE FUNCTION get_number_of_pending_users RETURN NUMBER
AS
    pending_users NUMBER;
BEGIN
    SELECT (SELECT COUNT(approved) FROM account WHERE approved = 0) INTO pending_users FROM dual;
    dbms_output.put_line('Count is ' || pending_users);
    RETURN pending_users;
END;
/



--Function to generate a formatted time stamp
CREATE OR REPLACE FUNCTION get_current_time RETURN VARCHAR2
AS
    time_right_now TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
    dbms_output.put_line('The current time is ' || time_right_now);

    RETURN to_char (time_right_now,'dd') || to_char (time_right_now,'mm') || to_char (time_right_now, 'yy') || to_char(time_right_now,'HH24');
END;
/

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

--Create a stored procedure to generate a new TiCo
CREATE OR REPLACE PROCEDURE insert_TiCo(new_accountnumber NUMBER)
AS
BEGIN
    INSERT INTO user_TiCo VALUES (new_accountnumber, null, get_current_time || '-' || new_accountnumber);
    COMMIT;
END;
/



--Create a stored procedure to insert new users
CREATE OR REPLACE PROCEDURE insert_user(new_firstname IN VARCHAR2, new_lastname IN VARCHAR2,
                            new_username IN VARCHAR2, new_password VARCHAR2)
AS
BEGIN
    INSERT INTO account a (a.accountnumber, a.firstname, a.lastname, a.username, a.password, a.balance, a.approved, a.locked, a.admin)
    VALUES (null, new_firstname, new_lastname, new_username, new_password, '0.00',0,0,0);
    COMMIT;
END;
/



--Create a stored procedure to approve users
CREATE OR REPLACE PROCEDURE approve_user(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET approved = 1 WHERE accountnumber = new_accountnumber AND approved <> 1;
    COMMIT;
END;
/



--Create a stored procedure to deny users
CREATE OR REPLACE PROCEDURE deny_user(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET approved = -1 WHERE accountnumber = new_accountnumber AND approved <> -1 AND admin <> 1;
    COMMIT;
END;
/



--Create a stored procedure to pend users
CREATE OR REPLACE PROCEDURE pend_user(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET approved = 0 WHERE accountnumber = new_accountnumber AND approved <> 0 AND admin <> 1;
    COMMIT;
END;
/



--Create a stored procedure to lock a user
CREATE OR REPLACE PROCEDURE lock_user(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET locked = 1 WHERE accountnumber = new_accountnumber AND locked <> 1 AND admin <> 1;
    COMMIT;
END;
/



--Create a stored procedure to unlock users
CREATE OR REPLACE PROCEDURE unlock_user(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET locked = 0 WHERE accountnumber = new_accountnumber AND locked <> 0 AND admin <> 1;
    COMMIT;
END;
/



--Create a stored procedure to delete users
CREATE OR REPLACE PROCEDURE delete_user(new_accountnumber NUMBER)
AS
BEGIN
    DELETE FROM account WHERE accountnumber = new_accountnumber AND admin <> 1;
    COMMIT;
END;
/



--Create a stored procedure to promote users
CREATE OR REPLACE PROCEDURE promote_admin(new_accountnumber NUMBER)
AS
BEGIN
    UPDATE account SET admin = 1 WHERE accountnumber = new_accountnumber;
    COMMIT;
END;
/



--Create a stored procedure for trade requests
CREATE OR REPLACE PROCEDURE make_trade_request(new_requestor NUMBER, new_acceptor NUMBER, new_amount NUMBER)
AS
BEGIN
    INSERT INTO trade_request VALUES (new_requestor,new_acceptor,new_amount, 0);
    COMMIT;
END;
/



--Create a stored procedure to accept a trade
CREATE OR REPLACE PROCEDURE accept_trade_request(new_requestor NUMBER, new_acceptor NUMBER, new_amount NUMBER)
AS
BEGIN
    UPDATE trade_request SET trade_accepted = 1 WHERE acceptor_accountnumber = new_acceptor;
    UPDATE user_TiCo SET accountnumber = new_acceptor WHERE accountnumber = new_requestor AND ROWNUM <= new_amount;
    COMMIT;
END;
/



--Create a stored procedure to deny a trade
CREATE OR REPLACE PROCEDURE deny_trade_request(new_requestor NUMBER, new_acceptor NUMBER, new_amount NUMBER)
AS
BEGIN
    UPDATE trade_request SET trade_accepted = -1 WHERE acceptor_accountnumber = new_acceptor;
    COMMIT;
END;
/

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

SELECT * FROM account;
SELECT * FROM user_TiCo;
SELECT * FROM trade_request;

COMMIT;
ROLLBACK;