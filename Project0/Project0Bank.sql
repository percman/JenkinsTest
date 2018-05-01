--Create a user for the database connection
CREATE USER bank_admin IDENTIFIED BY password;
--Granting access to features to the admin user
GRANT CONNECT TO bank_admin;
GRANT CREATE ANY TABLE TO bank_admin;
GRANT INSERT ANY TABLE TO bank_admin;
GRANT UPDATE ANY TABLE TO bank_admin;
GRANT DELETE ANY TABLE TO bank_admin;
GRANT CREATE ANY TRIGGER TO bank_admin;
GRANT CREATE ANY SEQUENCE TO bank_admin;
GRANT CREATE ANY PROCEDURE TO bank_admin;
GRANT UNLIMITED TABLESPACE TO bank_admin;

--Create employees table
CREATE TABLE employees(
    id NUMBER(10),
    username VARCHAR2(20),
    password VARCHAR2(100),
    fname VARCHAR2(20),
    lname VARCHAR2(20),
    CONSTRAINT PK_EMPLOYEEID PRIMARY KEY (id),
    CONSTRAINT UK_EMPLOYEEUSERNAME UNIQUE (USERNAME)
);

--Create table for customers
CREATE TABLE customers(
    id NUMBER(10),
    username VARCHAR2(20),
    password VARCHAR2(100),
    fname VARCHAR2(20),
    lname VARCHAR2(20),
    amount NUMBER(20) DEFAULT 0,
    isLocked NUMBER(1) DEFAULT 0,
    CONSTRAINT PK_CUSTOMERID PRIMARY KEY (id),
    CONSTRAINT UK_CUSTOMERUSERNAME UNIQUE (username)
);

--Create table for applying customers
CREATE TABLE applying_customers(
    id NUMBER(10),
    username VARCHAR2(20),
    password VARCHAR2(100),
    fname VARCHAR2(20),
    lname VARCHAR2(20),
    CONSTRAINT PK_APPLYINGID PRIMARY KEY (id),
    CONSTRAINT UK_APPLYINGUSERNAME UNIQUE (username)
);

--Sequence to incriment the primary key in employees table
CREATE SEQUENCE employee_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
      
--Sequence to incriment the primary key in customer table
CREATE SEQUENCE customer_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

--Sequence to incriment the primary key in applying customer table
CREATE SEQUENCE applying_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

--Hashing function that combines username, password, and a special word
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
    IS
    EXTRA VARCHAR2(10) := 'SALT';
    BEGIN
        RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
        INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
    END;
/

--Create a before insert trigger that will automatically increment the PK and hash the password
CREATE OR REPLACE TRIGGER employee_b_insert
    BEFORE INSERT
    ON employees
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN
            SELECT employee_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
        SELECT get_user_hash(:new.username, :new.password) INTO :new.password FROM dual;
    END;
/

--Create a before insert trigger that will automatically increment the PK and hash the password
CREATE OR REPLACE TRIGGER customer_b_insert
    BEFORE INSERT
    ON customers
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN
            SELECT customer_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
        SELECT get_user_hash(:new.username, :new.password) INTO :new.password FROM dual;
    END;
/

--Create a before insert trigger that will automatically increment the PK and hash the password
CREATE OR REPLACE TRIGGER applying_b_insert
    BEFORE INSERT
    ON applying_customers
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN
            SELECT applying_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
        SELECT get_user_hash(:new.username, :new.password) INTO :new.password FROM dual;
    END;
/

--Create a store procedure to insert new employees
CREATE OR REPLACE PROCEDURE insert_employee(new_username IN VARCHAR2, new_password IN VARCHAR2,
                                            new_fname IN VARCHAR2, new_lname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO employees ( username, password, fname, lname, id)
        VALUES (new_username, new_password, new_fname, new_lname, null);
        COMMIT;
    END;
/

--Create a store procedure to insert new customers
CREATE OR REPLACE PROCEDURE insert_customer(new_username IN VARCHAR2, new_password IN VARCHAR2,
                                            new_fname IN VARCHAR2, new_lname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO customers ( username, password, fname, lname, id)
        VALUES (new_username, new_password, new_fname, new_lname, null);
        COMMIT;
    END;
/

CREATE OR REPLACE PROCEDURE change_lock(search_id IN NUMBER)
    AS
        temp NUMBER(1);
        CURSOR c1 is
            SELECT isLocked
            FROM customers
            WHERE id = search_id;
    BEGIN
        open c1;
        fetch c1 into temp;
        close c1;       
        IF temp = 1 THEN
            UPDATE customers
                SET isLocked = 0
                WHERE id = search_id;
        ELSE
            UPDATE customers
                SET isLocked = 1
                WHERE id = search_id;
        END IF;
    END;
/

CREATE OR REPLACE PROCEDURE insert_applying(new_username IN VARCHAR2, new_password IN VARCHAR2,
                                            new_fname IN VARCHAR2, new_lname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO applying_customers (username, password, fname, lname, id)
        VALUES (new_username, new_password, new_fname, new_lname, null);
        COMMIT;
    END;
/

CREATE OR REPLACE PROCEDURE deposit(search_username IN VARCHAR2, deposited_amount IN NUMBER)
    AS
        old_amount NUMBER(20);
        CURSOR c1 IS
            SELECT amount
            FROM customers
            WHERE username = search_username;
    BEGIN
        OPEN c1;
        FETCH c1 INTO old_amount;
        CLOSE c1;
        UPDATE customers
            SET amount = amount + deposited_amount
            WHERE username = search_username;
        COMMIT;
    END;
/

CREATE OR REPLACE PROCEDURE withdraw(search_username IN VARCHAR2, deposited_amount IN NUMBER)
    AS
        old_amount NUMBER(20);
        CURSOR c1 IS
            SELECT amount
            FROM customers
            WHERE username = search_username;
    BEGIN
        OPEN c1;
        FETCH c1 INTO old_amount;
        CLOSE c1;
        UPDATE customers
            SET amount = amount - deposited_amount
            WHERE username = search_username;
        COMMIT;
    END;
/
--Insert the first employee
execute insert_employee ('test', 'test', 'test', 'test');

CREATE OR REPLACE PROCEDURE removeFromApplying
    AS
    BEGIN
        DELETE FROM applying_customers WHERE ROWNUM = 1;
    END;
/