/********************************************************************
Run the following lines with administrative access to your database
*********************************************************************/
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

/*************************************************************
Run the following code in the new jdbcbank_user connection
**************************************************************/

CREATE TABLE employee (
    employee_id NUMBER (100000),        -- (PK required)
    username VARCHAR2 (50),             -- (UK required) FK
    password VARCHAR2 (100)             -- (required)
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER (100000),   -- (PK required)
    requestor_id NUMBER (100000),       -- (FK NN required)
    approver_id NUMBER (100000),        -- (FK required)
    category_id NUMBER (1),             -- (required)
    status_id NUMBER (1)                -- (required)
);

CREATE TABLE employee_info (
    employee_id NUMBER (100000),        -- (PK FK required)
    f_name VARCHAR2 (50),            -- (NN required)
    m_initial VARCHAR2 (1),             -- (required)
    l_name VARCHAR2 (50),             -- (NN required)
    phone NUMBER (10),
    email VARCHAR2 (50),
    address VARCHAR2 (200),
    city VARCHAR2 (50),
    zip NUMBER (5),
    state_id NUMBER (2)
);

CREATE TABLE us_state (
    state_id NUMBER (2),
    state_name VARCHAR2 (14)
);

CREATE TABLE username (
    username VARCHAR2 (50),             -- PK
    f_manager NUMBER (1)                -- CK 0 or 1
);

CREATE TABLE f_manager (
    manager_id NUMBER (100000),         -- PK
    employee_id NUMBER (100000)         -- FK
);

CREATE TABLE r_status (
    status_id NUMBER (1),
    r_status VARCHAR2 (8)
);

CREATE TABLE r_category (
    category_id NUMBER (1),
    r_category VARCHAR2 (7)
);







