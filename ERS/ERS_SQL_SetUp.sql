-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- Run the following lines with administrative access to your database --
-------------------------------------------------------------------------
-------------------------------------------------------------------------
CREATE USER ers_user IDENTIFIED BY ers_password;
GRANT CONNECT TO ers_user;
GRANT CONNECT TO ers_user;
GRANT CREATE ANY TABLE TO ers_user;
GRANT UPDATE ANY TABLE TO ers_user;
GRANT DELETE ANY TABLE TO ers_user;
GRANT INSERT ANY TABLE TO ers_user;
GRANT UNLIMITED TABLESPACE TO ers_user;
GRANT DROP ANY TABLE TO ers_user;
GRANT CREATE ANY TRIGGER TO ers_user;
GRANT CREATE ANY SEQUENCE TO ers_user;
GRANT CREATE ANY PROCEDURE TO ers_user;


----------------------------------------------------------------
----------------------------------------------------------------
-- Run the following code in the new jdbcbank_user connection --
----------------------------------------------------------------
----------------------------------------------------------------

--------------------------------------------------------
-- Drop the names of the tables if they exist already --
--------------------------------------------------------
DROP TABLE reimbursement;
DROP TABLE employee_info;
DROP TABLE r_category;
DROP TABLE r_status;
DROP TABLE f_manager;
DROP TABLE employee;
--------------------------------------------------------
--------------------------------------------------------


----------------------------------------
-- Create the tables in this database --
----------------------------------------
CREATE TABLE employee (
    employee_id NUMBER (10),        -- (PK required)
    username VARCHAR2 (50) NOT NULL,             -- (UK required) FK
    password VARCHAR2 (100) NOT NULL,             -- (required)
    is_f_manager NUMBER (1) DEFAULT 0,                -- CK 0 or 1
    CONSTRAINT PK_EMPLOYEE_ID PRIMARY KEY (employee_id),
    CONSTRAINT UK_USERNAME UNIQUE (username),
    CONSTRAINT CK_IS_F_MANAGER CHECK (is_f_manager IN (0,1))
);

CREATE TABLE f_manager (
    f_manager_id NUMBER (10),         -- PK
    employee_id NUMBER (10),         -- FK
    CONSTRAINT PK_F_MANAGER_ID PRIMARY KEY (f_manager_id),
    CONSTRAINT FK_EMPLOYEE_ID_F_MANAGER FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE

);

CREATE TABLE r_status (
    status_id NUMBER (1),
    r_status VARCHAR2 (8),
    CONSTRAINT PK_STATUS_ID PRIMARY KEY (status_id),
    CONSTRAINT CK_R_STATUS CHECK (r_status IN ('pending', 'approved', 'denied'))
);

CREATE TABLE r_category (
    category_id NUMBER (1),
    r_category VARCHAR2 (7),
    CONSTRAINT PK_CATEGORY_ID PRIMARY KEY (category_id),
    CONSTRAINT CK_R_CATEGORY CHECK (r_category IN ('lodging', 'travel', 'food', 'other'))
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER (10),   -- (PK required)
    requestor_id NUMBER (10) NOT NULL,       -- (FK NN required)
    approver_id NUMBER (10),        -- (FK required)
    category_id NUMBER (1),             -- (required)
    status_id NUMBER (1),                -- (required)
    amount NUMBER (10,2),
    submitted TIMESTAMP,
    resolved TIMESTAMP,
    CONSTRAINT PK_REIMBURSEMENT_ID PRIMARY KEY (reimbursement_id),
    CONSTRAINT FK_REQUESTOR_ID FOREIGN KEY (requestor_id) REFERENCES employee (employee_id) ON DELETE CASCADE,
    CONSTRAINT FK_APPROVER_ID FOREIGN KEY (approver_id) REFERENCES f_manager (f_manager_id) ON DELETE CASCADE,
    CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES r_category (category_id) ON DELETE CASCADE,
    CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id) REFERENCES r_status (status_id) ON DELETE CASCADE
);

CREATE TABLE employee_info (
    employee_id NUMBER (10),        -- (PK FK required)
    f_name VARCHAR2 (50) NOT NULL,            -- (NN required)
    m_initial VARCHAR2 (1),             -- (required)
    l_name VARCHAR2 (50) NOT NULL,             -- (NN required)
    phone NUMBER (10),
    email VARCHAR2 (50),
--    address VARCHAR2 (200),
--    city VARCHAR2 (50),
--    zip NUMBER (5),
--    state_id NUMBER (2),
    CONSTRAINT PK_EMPLOYEE_ID_INFO PRIMARY KEY (employee_id),
    CONSTRAINT FK_EMPLOYEE_ID_INFO FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE
);

--CREATE TABLE us_state (
--    state_id NUMBER (2),
--    state_name VARCHAR2 (14)
--);
----------------------------------------
----------------------------------------

------------------------------------------------
-- Instantiate the status and category tables --
------------------------------------------------
INSERT INTO r_status (status_id, r_status) VALUES (1, 'pending');
INSERT INTO r_status (status_id, r_status) VALUES (2, 'approved');
INSERT INTO r_status (status_id, r_status) VALUES (3, 'denied');

INSERT INTO r_category (category_id, r_category) VALUES (1, 'lodging');
INSERT INTO r_category (category_id, r_category) VALUES (2, 'travel');
INSERT INTO r_category (category_id, r_category) VALUES (3, 'food');
INSERT INTO r_category (category_id, r_category) VALUES (4, 'other');
------------------------------------------------
------------------------------------------------


---------------------------------------------------------------------------------------
-- Create the sequences necessary for the employee, f_manager, and reimbursement ids -- 
---------------------------------------------------------------------------------------
CREATE SEQUENCE employee_id_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
   
CREATE SEQUENCE f_manager_id_sequence
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;
    
CREATE SEQUENCE reimbursement_id_sequence
    START WITH 100000
    INCREMENT BY 1
    NOCACHE;
---------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------


-------------------------------------------------------------------------------------------------
-- Create the triggers for before inserting into employee, f_manager, and reimbursement tables --
-------------------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER employee_b_insert
    BEFORE INSERT
    ON employee
    FOR EACH ROW
    BEGIN
        IF :new.employee_id IS NULL THEN
            SELECT employee_id_sequence.nextval 
                INTO :new.employee_id FROM dual;
        END IF;
        SELECT GET_USER_HASH (:new.username, :new.password)
            INTO :new.password FROM dual;        
    END;
    /

CREATE OR REPLACE TRIGGER f_manager_b_insert
    BEFORE INSERT
    ON f_manager
    FOR EACH ROW
    BEGIN
        IF :new.f_manager_id IS NULL THEN
            SELECT f_manager_id_sequence.nextval 
                INTO :new.f_manager_id FROM dual;
        END IF;
        IF :new.employee_id IS NULL THEN
            SELECT employee_id_sequence.currval
                INTO :new.employee_id FROM dual;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER reimbursement_b_insert
    BEFORE INSERT
    ON reimbursement
    FOR EACH ROW
    BEGIN
        IF :new.reimbursement_id IS NULL THEN
            SELECT reimbursement_id_sequence.nextval 
                INTO :new.reimbursement_id FROM dual;
        END IF;
        SELECT GET_CURRENT_TIME INTO :new.submitted FROM dual;
    END;
    /
    
CREATE OR REPLACE TRIGGER employee_info_b_insert
    BEFORE INSERT
    ON employee_info
    FOR EACH ROW
    BEGIN
        IF :new.employee_id IS NULL THEN
            SELECT employee_id_sequence.currval 
                INTO :new.employee_id FROM dual;
        END IF;
    END;
    /
-------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------
-- Hashing function that combines username, password, and a special word --   
---------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) 
                                            RETURN VARCHAR2
    IS
        EXTRA VARCHAR2(10) := 'YOLO';
    BEGIN
        RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
        INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
    END;
    /
---------------------------------------------------------------------------
---------------------------------------------------------------------------


--------------------------------------------------------------------------------
-- Function that returns the date in DD-MONTH-YY HH.MM.SSSSSSSSS AM/PM format -- 
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION GET_CURRENT_TIME RETURN TIMESTAMP
    AS
        time_right_now TIMESTAMP;
    BEGIN
        SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
        RETURN time_right_now;
    END;
    /
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------
-- Create stored procedures for inserting into employee, f_manager, and reimbursement tables -- 
-----------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE insert_employee (new_username VARCHAR2, new_password VARCHAR2, 
                                            new_f_name VARCHAR2, new_m_initial VARCHAR2, 
                                            new_l_name VARCHAR2, new_phone NUMBER, 
                                            new_email VARCHAR2)
    AS
    BEGIN
        INSERT INTO employee (employee_id, username, password)
            VALUES (null, new_username, new_password);
        INSERT INTO employee_info (employee_id, f_name, m_initial, l_name, phone, email)
            VALUES (null, new_f_name, new_m_initial, new_l_name, new_phone, new_email);
    END;
    /

CREATE OR REPLACE PROCEDURE insert_f_manager (new_username VARCHAR2, new_password VARCHAR2, 
                                            new_f_name VARCHAR2, new_m_initial VARCHAR2, 
                                            new_l_name VARCHAR2, new_phone NUMBER, 
                                            new_email VARCHAR2)
    AS
    BEGIN
        INSERT INTO employee (employee_id, username, password, is_f_manager)
            VALUES (null, new_username, new_password, 1);
        INSERT INTO employee_info (employee_id, f_name, m_initial, l_name, phone, email)
            VALUES (null, new_f_name, new_m_initial, new_l_name, new_phone, new_email);
        INSERT INTO f_manager (f_manager_id, employee_id)
            VALUES (null, null);
    END;
    /
    
CREATE OR REPLACE PROCEDURE insert_reimbursement (new_requestor_id NUMBER, new_category_id NUMBER, 
                                                new_status_id NUMBER, new_amount NUMBER) 
    AS
    BEGIN
        INSERT INTO reimbursement(reimbursement_id, requestor_id, category_id, status_id, amount)
            VALUES (null, new_requestor_id, new_category_id, new_status_id, new_amount);
    END;
    /
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------




