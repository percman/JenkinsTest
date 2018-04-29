--sequence for id
CREATE SEQUENCE credential_id_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NOCACHE;

--Trigger for auto incrementin ID    
CREATE OR REPLACE TRIGGER credential_b_insert
    BEFORE INSERT
    ON credentials
    FOR EACH ROW
    BEGIN
        IF :new.id IS NULL THEN 
            SELECT credential_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
        SELECT GET_USER_HASH(:new.username,:new.password)INTO :new.password FROM dual;
    END;
    /
    
--sequence for Account
CREATE SEQUENCE account_id_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NOCACHE;
    
SELECT account_id_sequence.CURRVAL FROM DUAL;
SELECT credential_id_sequence.NEXTVAL FROM DUAL;

ALTER SEQUENCE account_id_sequence INCREMENT by 1;
ALTER SEQUENCE credential_id_sequence INCREMENT by -1;


--Trigger for auto incrementin accountID    
CREATE OR REPLACE TRIGGER account_b_insert
    BEFORE INSERT
    ON account
    FOR EACH ROW
    BEGIN
        IF :new.account_id IS NULL THEN 
            SELECT account_id_sequence.nextval INTO :new.account_id FROM dual;
        END IF;
    END;
    /
    
--Strored procedure for Inserting into credentials    
CREATE OR REPLACE PROCEDURE insert_credentials(user_name IN VARCHAR2, user_password IN VARCHAR2)
AS
BEGIN
    INSERT INTO credentials (username, password) VALUES(user_name, user_password);
    COMMIT;
END;
/

--Procedure for deleting
CREATE OR REPLACE PROCEDURE delete_credentials(user_name IN VARCHAR2)
AS
BEGIN
    DELETE FROM credentials WHERE username = user_name;
    COMMIT;
END;
/

--Procedure to insert a users accessiblilty
CREATE OR REPLACE PROCEDURE insert_account_access(user_name IN VARCHAR2)
AS
BEGIN
    INSERT INTO account_access (username) VALUES (user_name);
    COMMIT;
END;
/

--UDF that returns a hash of the users password
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

--UDF that returns the funds in a users account
CREATE OR REPLACE FUNCTION GET_ACCOUNT_FUNDS(id NUMBER, user_name VARCHAR2) RETURN NUMBER
AS
    money NUMBER;
BEGIN
    SELECT funds INTO money FROM account WHERE account_id = id AND username = user_name;
    RETURN money;
END;
/
