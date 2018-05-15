--CREATE user ersadmin
--IDENTIFIED BY admin1
--DEFAULT TABLESPACE users
--TEMPORARY TABLESPACE temp
--QUOTA 10M ON USERS;
--
--GRANT connect to ersadmin;
--GRANT resource to ersadmin;
--GRANT create session TO ersadmin;
--GRANT create table TO ersadmin;
--GRANT create view TO ersadmin;
--GRANT CREATE ANY TABLE TO ersadmin ;
--GRANT INSERT ANY TABLE TO ersadmin ;
--GRANT UPDATE ANY TABLE TO ersadmin ;
--GRANT DELETE ANY TABLE TO ersadmin ;
--GRANT CREATE ANY PROCEDURE TO ersadmin ;
--GRANT CREATE ANY TRIGGER TO ersadmin ;
--GRANT CREATE ANY SEQUENCE TO ersadmin;
--GRANT UNLIMITED TABLESPACE TO ersadmin;

--ersadmin/admin1

CREATE TABLE Employee(
    eId NUMBER(10) PRIMARY KEY,
    eFName VARCHAR2(15) NOT NULL,
    eLName VARCHAR2(15) NOT NULL,
    eUsername VARCHAR2 (15) UNIQUE,
    ePassword VARCHAR2(15),                     -- CHANGED TO VARCHAR2(35)
    eType VARCHAR2(2) NOT NULL               -- GE= General Employee/ FM = Finance Mgr   // EX= Executive
    );
    
ALTER TABLE EMPLOYEE
MODIFY  EPASSWORD VARCHAR(35);

CREATE TABLE Reimbursement(
    rID NUMBER(10) PRIMARY KEY,
    eID_FK  NUMBER(10) ,
    approvedBy_FK NUMBER(10),
    rejectedBy_FK NUMBER(10),
    status CHAR(1) NOT NULL,                -- Approved(a)/Rejected(r)
    image BLOB,
    CONSTRAINT eId_FK FOREIGN KEY(eID_FK) REFERENCES Employee(eId),
    CONSTRAINT approvedBy_FK FOREIGN KEY(approvedBy_FK) REFERENCES Employee(eId),
    CONSTRAINT rejectedBy_FK FOREIGN KEY(rejectedBy_FK) REFERENCES Employee(eId)
    
    );
    
ALTER TABLE Reimbursement
    ADD( approveDate TIMESTAMP,
         rejectedDtate TIMESTAMP,
         reimbAmount NUMBER(10)
         );

ALTER TABLE Reimbursement
RENAME COLUMN rejectedDtate TO rejectedDate;


CREATE TABLE Category(
    rID NUMBER(10) PRIMARY KEY,
    lodging VARCHAR2(15),
    travel VARCHAR2(15),
    food VARCHAR2(15),
    other VARCHAR2(15),
    CONSTRAINT rID_FK FOREIGN KEY(rID) REFERENCES Reimbursement(rID)
);

CREATE SEQUENCE employee_id_sequence
    MINVALUE 0
    MAXVALUE 1000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
CREATE SEQUENCE reimbursement_id_sequence
    MINVALUE 0
    MAXVALUE 1000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
CREATE OR REPLACE TRIGGER employee_trigger_insert
BEFORE INSERT
ON Employee
FOR EACH ROW
BEGIN
    IF : new.eID IS NULL THEN
        SELECT employee_id_sequence.nextval INTO: new.eId FROM dual;
    END IF;
    
    SELECT GET_USER_HASH(:NEW.EUSERNAME , :NEW.EPASSWORD) INTO :NEW.EPASSWORD FROM DUAL;
        
END;
/  

CREATE OR REPLACE TRIGGER reimbursement_trigger_insert
BEFORE INSERT
ON Reimbursement
FOR EACH ROW
BEGIN
    IF : new.rID IS NULL THEN
        SELECT reimbursement_id_sequence.nextval INTO: new.rId FROM dual;
    END IF;
END;
/  

insert all
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'jack','lee','jacku', 'leep','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'rory','mcdonald','roryu', 'mcdonaldp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'jamie','palmer','jamieu ', 'palmerp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'matthew','wills','matthewu ', 'willsp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'zac','gibson','zacu ', 'gibsonp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'jordan','holland','joardanu ', 'hollandp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'george','grant','georgeu', 'grantp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'felipe','sherman','felipeu', 'shermanp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'reagan','mcfadden','reaganu', 'mcfaddenp','ge')
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'luis','barker','luisu', 'barkerp','fm')          --FINANCE MGR
into employee(eid,efname, elname, eusername,epassword, etype) values(null,'sofia','holmes','sofiau', 'holmesp','ex')        --THE BOSS
select 1 from dual;

insert all
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,68,77,null,'a',CURRENT_TIMESTAMP,null,100)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,69,77,null,'a',CURRENT_TIMESTAMP,null,80)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,70,77,null,'a',CURRENT_TIMESTAMP,null,30)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,71,null,77,'r',null,CURRENT_TIMESTAMP,30)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,72,null,77,'r',null,CURRENT_TIMESTAMP,90)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,73,77,null,'a',CURRENT_TIMESTAMP,null,20)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,74,null,77,'r',null,CURRENT_TIMESTAMP,70)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,75,77,null,'a',CURRENT_TIMESTAMP,null,60)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,76,null,77,'r',null,CURRENT_TIMESTAMP,60)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,68,77,null,'a',CURRENT_TIMESTAMP,null,500)
into reimbursement(rid,eid_fk, approvedby_fk, rejectedby_fk,status,approveDate,rejectedDate,reimbAmount) values(null,69,77,null,'a',CURRENT_TIMESTAMP,null,300)
select 1 from dual;


CREATE OR REPLACE PROCEDURE check_my_login(uname IN VARCHAR2, upwd IN VARCHAR2, verify1 OUT NUMBER)
IS
    username11 VARCHAR2(10) := uname;
    password11 VARCHAR2(10) := upwd;
BEGIN
    SELECT 1 INTO verify1
    FROM EMPLOYEE
    WHERE EUSERNAME = username11 AND EPASSWORD = password11;
END;
/

--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD 
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

