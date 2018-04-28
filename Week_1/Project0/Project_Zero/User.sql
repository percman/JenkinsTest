--Create admin
CREATE USER cameron identified by passwerd;
--Create table
CREATE TABLE userTable(
userName VARCHAR(60),
balance INT,
isAdmin INT,
isLocked INT,
isApproved INT,
CONSTRAINT PK_USERNAME PRIMARY KEY (userName)
)
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
CREATE OR REPLACE PROCEDURE insert_user(u_name IN VARCHAR, u_balance IN INT, u_admin IN INT, u_locked IN INT, u_approved IN INT)
AS
BEGIN
    --INSERT INTO user VALUES (USERNAME BALANCE, ISADMIN, ISLOCKED, ISAPPROVED);
    INSERT INTO USERTABLE (username, balance, isadmin, islocked, isapproved) 
       VALUES (u_name, u_balance, u_admin, u_locked, u_approved);
END;
/

DELETE FROM USERTABLE where username = 'Gimli';

--Update USERTABLE
CREATE OR REPLACE PROCEDURE update_user(u_name IN VARCHAR, u_balance IN INT, u_admin IN INT, u_locked IN INT, u_approved IN INT)
AS
BEGIN
    UPDATE USERTABLE SET username = u_name, Balance = u_balance, isAdmin = u_admin, isLocked = u_locked, isApproved = u_approved
    WHERE username = u_name;
END;
/

UPDATE USERTABLE SET username = 'Frodo', Balance = 150, isAdmin = 1,
isLocked = 0, isApproved = 1 where username = 'Frodo';
COMMIT;