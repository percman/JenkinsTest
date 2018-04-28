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

SELECT * FROM USERTABLE;

ALTER TABLE userTable ADD userId INT;

--Grant privileges
GRANT INSERT ON userTable TO cameron;
GRANT UPDATE ON userTable TO cameron;
GRANT SELECT ON userTable TO cameron;
GRANT DELETE ON userTable TO cameron;
GRANT CREATE SESSION TO cameron;

SELECT * FROM USERTABLE;

ROLLBACK;
--Transaction to delete a user
 CREATE OR REPLACE PROCEDURE delete_user(user_name IN VARCHAR)
 AS
 BEGIN
    DELETE FROM usertable WHERE username = user_name;
 END;
 /

--Creating a stored procudure to insert users
CREATE OR REPLACE PROCEDURE user(u_name IN VARCHAR, u_balance IN INT, u_admin IN INT, u_locked IN INT, u_approved IN INT)
AS
BEGIN
    --INSERT INTO user VALUES (USERNAME BALANCE, ISADMIN, ISLOCKED, ISAPPROVED);
    INSERT INTO userTable (username, balance, isadmin, islocked, isapproved) 
    VALUES (u_name, u_balance, u_admin, u_locked, u_approved);
END;
/

DELETE FROM USERTABLE where username = 'Gimli';


CREATE SEQUENCE user_sequence
    START WITH 1
    MINVALUE 0
    NOCACHE;


CREATE OR REPLACE TRIGGER user_before_insert
    BEFORE INSERT
    ON userTable
    FOR EACH ROW
    BEGIN
        IF: new.userId IS NULL THEN
        SELECT user_sequence.nextval INTO :new.userId FROM dual;
        END IF;
        dbms_output.put_line('User inserted' || user_sequence.currval);
    END;
    /