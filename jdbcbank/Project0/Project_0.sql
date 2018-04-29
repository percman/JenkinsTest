--Create DB, grant permissions in admin

--CREATE USER project_0 IDENTIFIED BY bank_pword;
--
--GRANT CONNECT TO project_0;
--GRANT CREATE ANY TABLE TO project_0;
--GRANT UPDATE ANY TABLE TO project_0;
--GRANT DELETE ANY TABLE TO project_0;
--GRANT INSERT ANY TABLE TO project_0;
--GRANT CREATE ANY PROCEDURE TO project_0;
--GRANT CREATE ANY TRIGGER TO project_0;
--GRANT CREATE ANY SEQUENCE TO project_0;
--GRANT UNLIMITED TABLESPACE TO project_0;
-- In project_0 DB:

CREATE TABLE person(
user_id NUMBER(10),
username VARCHAR2(30) NOT NULL,
password VARCHAR2(100) NOT NULL,
role VARCHAR2(5) NOT NULL,
balance NUMBER(10, 2) DEFAULT 0,
approved VARCHAR2(1) DEFAULT 'F',
locked VARCHAR2(1) DEFAULT 'F',
CONSTRAINT PK_PERSON PRIMARY KEY (user_id),
CONSTRAINT UNIQUE_USERNAME UNIQUE (username),
CONSTRAINT POSITIVE_BALANCE CHECK (balance >= 0),
CONSTRAINT USER_ADMIN CHECK (role IN ('User', 'Admin')),
CONSTRAINT TF_APPROVED CHECK (approved IN('T', 'F')),
CONSTRAINT TF_LOCKED CHECK (locked IN ('T', 'F'))
);

CREATE TABLE transaction(
transaction_id NUMBER(10),
user_id NUMBER(10) NOT NULL,
action VARCHAR(10) NOT NULL,
amount NUMBER(10, 2) NOT NULL,
transaction_time TIMESTAMP NOT NULL,
CONSTRAINT PK_TRANSACTION PRIMARY KEY (transaction_id),
CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES person (user_id),
CONSTRAINT DEP_WITH CHECK (action IN ('Deposit', 'Withdrawal')),
CONSTRAINT POSITIVE_ONLY CHECK (amount > 0)
);

CREATE SEQUENCE person_id_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
CREATE SEQUENCE transaction_id_sequence
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

CREATE OR REPLACE TRIGGER person_insert
BEFORE insert
ON person
FOR EACH ROW
BEGIN
    IF :new.user_id IS NULL THEN
        SELECT person_id_sequence.nextval INTO :new.user_id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

CREATE OR REPLACE PROCEDURE insert_person(new_username IN VARCHAR2, new_password IN VARCHAR2,
                            new_role IN VARCHAR2)
AS
BEGIN
    INSERT INTO person(username, password, role)
    VALUES (new_username, new_password, new_role);
    COMMIT;
END;
/

CREATE OR REPLACE FUNCTION get_current_time RETURN TIMESTAMP
AS
    time_right_now TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
    RETURN time_right_now;
END;
/

CREATE OR REPLACE TRIGGER transaction_insert
BEFORE insert
ON transaction
FOR EACH ROW
BEGIN
    IF :new.transaction_id IS NULL THEN
        SELECT transaction_id_sequence.nextval INTO :new.transaction_id FROM dual;
    END IF;
    IF :new.transaction_time IS NULL THEN
        SELECT CURRENT_TIMESTAMP INTO :new.transaction_time FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insert_transaction(user_name IN VARCHAR2, trans_action IN VARCHAR2, 
                            trans_amount IN NUMBER)
AS
    userid NUMBER(10);
BEGIN
    SELECT user_id INTO userid FROM person WHERE user_name = username;
    INSERT INTO transaction (user_id, action, amount) VALUES (userid, trans_action, trans_amount);
END;
/

CREATE OR REPLACE PROCEDURE withdraw_cash(user_name IN VARCHAR2, cash IN NUMBER)
AS
    userid NUMBER(10);
BEGIN
    SELECT user_id INTO userid FROM person WHERE username = user_name;
    INSERT INTO transaction (user_id, action, amount) VALUES (userid, 'Withdrawal', cash);
    UPDATE person SET balance = (balance-cash) WHERE username = user_name;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE deposit_cash(user_name IN VARCHAR2, cash IN NUMBER)
AS
    userid NUMBER(10);
BEGIN
    SELECT user_id INTO userid FROM person WHERE username = user_name;
    INSERT INTO transaction (user_id, action, amount) VALUES (userid, 'Deposit', cash);
    UPDATE person SET balance = (balance+cash) WHERE username = user_name; 
    COMMIT;
END;
/

CREATE OR REPLACE FUNCTION last_transaction(userid IN VARCHAR2) RETURN TIMESTAMP
AS
    last_time TIMESTAMP;
BEGIN
    SELECT MAX(transaction_time) INTO last_time FROM transaction
    WHERE user_id = userid;
    RETURN last_time;
END;
/

CREATE OR REPLACE FUNCTION ledger_balance(t_time IN TIMESTAMP) RETURN NUMBER
AS
    current_balance NUMBER(10, 2);
BEGIN

    SELECT SUM(
        CASE
            WHEN action = 'Deposit' THEN amount
            ELSE amount*-1
        END)       
    INTO current_balance
    FROM transaction
    WHERE t_time>=transaction_time;
    
    RETURN current_balance;
END;
/