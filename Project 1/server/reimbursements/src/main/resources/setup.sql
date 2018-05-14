CREATE USER reimbursement identified by hunter2;

GRANT CONNECT to reimbursement;
GRANT CREATE ANY TABLE to reimbursement;
GRANT INSERT ANY TABLE TO reimbursement;
GRANT UPDATE ANY TABLE TO reimbursement;
GRANT DELETE ANY TABLE TO reimbursement;
GRANT CREATE ANY PROCEDURE TO reimbursement;
GRANT CREATE ANY TRIGGER TO reimbursement;
GRANT CREATE ANY SEQUENCE TO reimbursement;
GRANT UNLIMITED TABLESPACE TO reimbursement;

conn reimbursement/hunter2


-- EID size has been limited to three digits, for a larger company 
-- it's advised to increase this value.
CREATE TABLE Employee (
eid NUMBER(3),
username VARCHAR2(30) UNIQUE,
password VARCHAR2(100),
CONSTRAINT PK_EIDE PRIMARY KEY(eid)
);

CREATE TABLE EInfo (
eid NUMBER(3) PRIMARY KEY,
first_name VARCHAR(18),
last_name VARCHAR(25),
address VARCHAR(200),
CONSTRAINT FK_EIDE FOREIGN KEY (eid) REFERENCES Employee(eid)
);

CREATE TABLE FManager (
eid NUMBER(3),
CONSTRAINT FK_EID FOREIGN KEY (eid) REFERENCES Employee(eid),
CONSTRAINT PK_EIDF PRIMARY KEY(eid)
);

CREATE TABLE RCategory (
cid NUMBER(1) PRIMARY KEY,
name VARCHAR(7))
;

CREATE TABLE RStatus (
sid NUMBER(1) PRIMARY KEY,
name VARCHAR(9))
;

CREATE TABLE Reimbursement (
rid NUMBER,
requester NUMBER(3),
approver NUMBER(3),
category NUMBER(1),
amount NUMBER(6, 2),
status NUMBER(1),
CONSTRAINT FK_REQUESTER FOREIGN KEY (requester) REFERENCES Employee(eid),
CONSTRAINT FK_APPROVER FOREIGN KEY (approver) REFERENCES FManager(eid),
CONSTRAINT CK_RA_NOT_EQUAL CHECK (requester <> approver),
CONSTRAINT PK_RID PRIMARY KEY(rid),
CONSTRAINT FK_STATUS FOREIGN KEY(status) REFERENCES RStatus(sid),
CONSTRAINT FK_CATEGORY FOREIGN KEY(category) REFERENCES RCategory(cid)
);


CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

INSERT INTO RCategory VALUES (0, 'Lodging');
INSERT INTO RCategory VALUES (1, 'Travel');
INSERT INTO RCategory VALUES (2, 'Food');
INSERT INTO RCategory VALUES (3, 'Other');

INSERT INTO RStatus VALUES (0, 'Pending');
INSERT INTO RStatus VALUES (1, 'Approved');
INSERT INTO RStatus VALUES (2, 'Denied');

INSERT INTO Employee VALUES (0, 'WalkinDude', GET_USER_HASH('WalkinDude', 'WalkinWalkin'));
INSERT INTO EInfo VALUES (0, 'Curtis', 'Hilgenberg', '123 Fake Street' );

INSERT INTO Employee VALUES (1, 'AdminDude', GET_USER_HASH('AdminDude', 'AdminAdmin'));
INSERT INTO EInfo VALUES (1, 'Curt', 'Hilgenberg', '1 Grand Avenue' );
INSERT INTO Fmanager VALUES (1);

INSERT INTO Employee VALUES (-1, 'N/A', 'N/A');
INSERT INTO EINFO VALUES (-1, 'N/A', 'N/A', 'N/A');
INSERT INTO FManager VALUES (-1);

SELECT * FROM FManager;

COMMIT;

CREATE SEQUENCE reimbursement_id_sequence
  START WITH 1
  INCREMENT BY 1;

CREATE OR REPLACE FUNCTION AUTH_EMPLOYEE(username_in VARCHAR2, password_in VARCHAR2) RETURN NUMBER
IS
  output NUMBER;
  BEGIN
    SELECT count(*) into output FROM EMPLOYEE WHERE username = username_in
                                                    AND password = GET_USER_HASH(username_in, password_in);
    RETURN output;
  END;
/


CREATE OR REPLACE PROCEDURE add_reimbursement(username_in VARCHAR2, password_in VARCHAR2,
  category_in NUMBER, amount_in NUMBER)
IS
  requesterID number;
  BEGIN
    IF (AUTH_EMPLOYEE(username_in, password_in) = 1) THEN
      SELECT eid INTO requesterID FROM Employee where username_in = username;
      INSERT INTO Reimbursement (rid, requester, approver, category, amount, status)
        VALUES (reimbursement_id_sequence.nextval, requesterID, -1, category_in, amount_in, 0);
    end if;
    COMMIT;
  END;
/

CREATE OR REPLACE FUNCTION RSTATUS_CHECK(status_in NUMBER, rid_in NUMBER, approver_in NUMBER) RETURN NUMBER
IS
  output NUMBER;
  BEGIN
    SELECT status INTO output FROM Reimbursement WHERE rid_in = rid;
    IF (output <> 0) THEN
      RETURN -1;
    END IF;
    SELECT count(*) into output FROM Reimbursement WHERE requester = approver_in AND rid = rid_in;
    IF (output <> 0) THEN
      RETURN -2;
    END IF;
    RETURN 1;
  END;
/