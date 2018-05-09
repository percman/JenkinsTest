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
    is_f_manager NUMBER (1),                -- CK 0 or 1
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


