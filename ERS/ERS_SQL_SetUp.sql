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
    employee_id NUMBER (100000),    -- PK required
    username VARCHAR2 (100),        -- UK required
    password VARCHAR2 (500)         -- required
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER (100000),   -- PK required
    requestor_id NUMBER (100000),       -- FK required
    approver_id NUMBER (100000),        -- FK required
    category_id NUMBER (1),             -- required
    status_id NUMBER (1)                -- required
);

CREATE TABLE employee_info (
    employee_id NUMBER (100000),        -- PK FK required
    firstname VARCHAR2 (100),           -- NN required
    middleinitial VARCHAR2 (1),         -- required
    lastname VARCHAR2 (100)             -- NN required
);

CREATE TABLE username (
    

);