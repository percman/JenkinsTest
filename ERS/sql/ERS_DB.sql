--CREATE USER projectOne IDENTIFIED BY password;
--GRANT CREATE SEQUENCE TO projectOne;
--GRANT CREATE PROCEDURE TO projectOne;
--GRANT CREATE TRIGGER TO projectOne;
--GRANT UNLIMITED TABLESPACE TO projectOne;
--GRANT CREATE SESSION TO projectOne;
--GRANT CREATE TABLE TO projectOne;



CREATE TABLE employee(
    e_id NUMBER PRIMARY KEY,
    title VARCHAR(20)NOT NULL,
    username VARCHAR(30) UNIQUE,
    password VARCHAR(200)NOT NULL
);

CREATE TABLE information(
    e_id NUMBER PRIMARY KEY,
    f_name VARCHAR2(30) NOT NULL,
    l_name VARCHAR2(30) NOT NULL,
    telephone VARCHAR2(30),
    CONSTRAINT FK_employee FOREIGN KEY(e_id) REFERENCES employee(e_id)
);

CREATE TABLE reimbursement(
    r_id NUMBER PRIMARY KEY,
    request_id NUMBER NOT NULL,
    manager_id NUMBER,
    CONSTRAINT FK_request FOREIGN KEY(request_id) REFERENCES employee(e_id),
     CONSTRAINT FK_manager FOREIGN KEY(manager_id) REFERENCES employee(e_id)
);

CREATE TABLE reimbursement_info(
    r_id NUMBER UNIQUE,
    category VARCHAR2(30) NOT NULL,
    status VARCHAR2(10) DEFAULT('pending'),
    CONSTRAINT FK_reimbursement FOREIGN KEY(r_id) REFERENCES reimbursement(r_id)
);