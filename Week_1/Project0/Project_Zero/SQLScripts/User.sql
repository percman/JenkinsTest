--Create admin
CREATE USER cameron identified by passwerd;
--Create table
CREATE TABLE userTable(
userId INT,
userName VARCHAR(60),
balance INT,
isAdmin INT,
isLocked INT,
isApproved INT,
CONSTRAINT PK_USERNAME PRIMARY KEY (userName)
)
--Create a transaction table
CREATE TABLE transactions (
transId INT,
profit INT,
CONSTRAINT PK_ID PRIMARY KEY (transId)
)

--Grant privileges
GRANT INSERT ON transactions TO cameron;
GRANT UPDATE ON transactions TO cameron;
GRANT SELECT ON transactions TO cameron;
GRANT DELETE ON transactions TO cameron;
GRANT CREATE SESSION TO cameron;

--Procedure to delete a user
 CREATE OR REPLACE PROCEDURE delete_user(user_name IN VARCHAR)
 AS
 BEGIN
    DELETE FROM usertable WHERE username = user_name;
 END;
 /
--Procedure to update user
CREATE OR REPLACE PROCEDURE update_user(u_name IN VARCHAR, u_balance IN INT, u_admin IN INT, u_locked IN INT, u_approved IN INT)
AS
BEGIN
    UPDATE USERTABLE SET
    balance = u_balance, 
    isadmin = u_admin, 
    islocked = u_locked, 
    isapproved = u_approved WHERE username = u_name;
END;
/
--Stored procudure to insert users
CREATE OR REPLACE PROCEDURE insert_user(u_name IN VARCHAR, u_balance IN INT, u_admin IN INT, u_locked IN INT, u_approved IN INT)
AS
BEGIN
    --INSERT INTO user VALUES (USERNAME BALANCE, ISADMIN, ISLOCKED, ISAPPROVED);
    INSERT INTO userTable (username, balance, isadmin, islocked, isapproved) 
    VALUES (u_name, u_balance, u_admin, u_locked, u_approved);
END;
/

CREATE SEQUENCE user_sequence
    START WITH 1
    MINVALUE 0
    NOCACHE;

CREATE SEQUENCE trans_sequence
    START WITH 1
    MINVALUE 0
    MAXVALUE 9000
    NOCACHE;

--Iterate through transactions
CREATE OR REPLACE TRIGGER trans_before_insert
    BEFORE INSERT
    ON TRANSACTIONS
    FOR EACH ROW
    BEGIN
        IF: new.transid IS NULL THEN
        SELECT trans_sequence.nextval INTO :new.transid FROM dual;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER user_before_insert
    BEFORE INSERT
    ON userTable
    FOR EACH ROW
    BEGIN
        IF: new.userId IS NULL THEN
        SELECT user_sequence.nextval INTO :new.userId FROM dual;
        END IF;
    END;
    /

--Function for getting total users
CREATE OR REPLACE FUNCTION get_total_users RETURN INT
IS
    total_users INT; 
BEGIN
    SELECT COUNT(username) INTO total_users FROM usertable;
    return total_users;
END;

--Function for getting total total balance
CREATE OR REPLACE FUNCTION get_total_balance RETURN INT
IS
    total_balance INT; 
    CURSOR c1 IS SELECT SUM(balance) FROM USERTABLE;
BEGIN
    OPEN c1;
    FETCH c1 INTO total_balance;
    return total_balance;
END;

CREATE OR REPLACE PROCEDURE callTotalUsers AS
    n INT(11);
BEGIN 
    n := get_total_users;
END;
/

SELECT username, balance, isadmin, islocked, isapproved FROM USERTABLE;