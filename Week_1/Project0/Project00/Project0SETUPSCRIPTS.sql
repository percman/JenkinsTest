--CREATE TABLE
CREATE TABLE BANK_CUSTOMERS(
accountnum INTEGER,
firstname VARCHAR2(20),
lastname VARCHAR2(20),
email VARCHAR2(20) NOT NULL,
password VARCHAR2(20) NOT NULL,
active NUMBER DEFAULT 0,
balance NUMBER DEFAULT 0,
admin NUMBER,
CONSTRAINT PK_BANK PRIMARY KEY (ACCOUNTNUM),
CONSTRAINT UK_USERNAME UNIQUE(EMAIL)
);
/

--CREATE HARDCODED ADMIN ACCOUNT AS FIRST ENTRY
INSERT INTO BANK_CUSTOMERS VALUES(100000, 'David', 'Prado', 'mrprado@gmail.com', 'password', 1, 0, 1);


--SEQUENCE FOR AUTOGENERATED ACCOUNT NUMBERS
CREATE SEQUENCE accountnumber_sequence start with 100001 increment by 1;

--TRIGGER FOR AUTOGENERATED ACCOUNT NUMBERS
CREATE OR REPLACE TRIGGER customer_insert
    BEFORE INSERT
    ON BANK_CUSTOMERS
    FOR EACH ROW
    WHEN (NEW.ACCOUNTNUM IS NULL
    )
    BEGIN
        SELECT accountnumber_sequence.NEXTVAL
        INTO :NEW.ACCOUNTNUM
        FROM DUAL;      
    END;
    /
    
    
--PROCEDURE FOR NEW USERS   
create or replace PROCEDURE new_user( firstname IN VARCHAR2, lastname IN VARCHAR2, email IN VARCHAR2, 
                                    password IN VARCHAR2, active IN NUMBER, balance IN NUMBER, admin IN NUMBER)
AS
BEGIN
    INSERT INTO bank_customers VALUES(null,firstname, lastname, email, password, active, balance,admin);
    COMMIT;
END;
/   

--PROCEDURE TO ACTIVATE AN ACCOUNT    
create or replace PROCEDURE active(target IN NUMBER)
AS
BEGIN
    UPDATE bank_customers SET active = 1
    WHERE accountnum = target;
    COMMIT;
END;
/

--PROCEDURE TO CHANGE EMAIL
create or replace PROCEDURE change_email(target IN NUMBER, new_name IN VARCHAR2)
AS
BEGIN
    UPDATE bank_customers SET email = new_name
    WHERE accountnum = TARGET;
    COMMIT;
END;
/

--PROCEDURE TO CHANGE FIRST NAME
create or replace PROCEDURE change_first_name(target IN NUMBER, new_name IN VARCHAR2)
AS
BEGIN
    UPDATE bank_customers SET firstname = new_name
    WHERE accountnum = target;
    COMMIT;
END;
/
--PROCEDURE TO CHANGE LAST NAME
create or replace PROCEDURE change_last_name(target IN NUMBER, new_name IN VARCHAR2)
AS
BEGIN
    UPDATE bank_customers SET lastname = new_name
    WHERE accountnum = TARGET;
    COMMIT;
END;
/
--PROCEDURE TO CHANGE PASSWORD
create or replace PROCEDURE change_password(target IN NUMBER, new_pw IN VARCHAR2)
AS
BEGIN
    UPDATE bank_customers SET password = new_pw
    WHERE accountnum = target;
    COMMIT;
END;
/

create or replace PROCEDURE close_account(target IN NUMBER)
AS
BEGIN
    DELETE FROM bank_customers
    WHERE accountnum = target;
    COMMIT;
END;
/

create or replace PROCEDURE deposit(target IN NUMBER, amount IN NUMBER)
AS
BEGIN
    UPDATE bank_customers SET balance = balance + amount
    WHERE accountnum = target;
    COMMIT;
END;
/

create or replace PROCEDURE get_user(target IN VARCHAR2, user_cursor OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN user_cursor FOR
    SELECT * FROM bank_customers
    WHERE email = target;
END;
/

create or replace PROCEDURE new_user( firstname IN VARCHAR2, lastname IN VARCHAR2, email IN VARCHAR2, 
                                    password IN VARCHAR2, active IN NUMBER, balance IN NUMBER, admin IN NUMBER)
AS
BEGIN
    INSERT INTO bank_customers VALUES(null,firstname, lastname, email, password, active, balance,admin);
    COMMIT;
END;
/

create or replace PROCEDURE transfer (target IN NUMBER, source IN NUMBER, amount IN NUMBER)
AS
BEGIN
    UPDATE bank_customers SET balance = balance-amount
    WHERE accountnum = source;
    UPDATE bank_customers SET balance = balance+amount
    WHERE accountnum = target;
    COMMIT;
END;
/

create or replace PROCEDURE withdrawal (target IN NUMBER, amount IN NUMBER)
AS
BEGIN
    UPDATE bank_customers SET balance = balance - amount
    WHERE accountnum = target;
    COMMIT;
END;
/



    


    

 