CREATE USER project0admin IDENTIFIED BY password;
GRANT CREATE SEQUENCE TO project0admin;
GRANT CREATE PROCEDURE TO project0admin;
GRANT CREATE TRIGGER TO project0admin;

CREATE TABLE credentials(
    id NUMBER ,
    username VARCHAR2(20) UNIQUE,
    password VARCHAR(20),
    CONSTRAINT PK_USER PRIMARY KEY(id)
    );
    
CREATE TABLE account (
    account_id NUMBER,
    username VARCHAR2(20),
    funds FLOAT CHECK(funds >=0),
    CONSTRAINT PK_ACCOUNT PRIMARY KEY(account_id),
    CONSTRAINT FK_ACCOUNT FOREIGN KEY (username) REFERENCES credentials(username)
    ON DELETE CASCADE
    );
    
    
CREATE TABLE account_access(
    username VARCHAR2(20)UNIQUE,
    locked VARCHAR2(5) DEFAULT 'false',
    permissions VARCHAR2(5)DEFAULT 'false',
    pending VARCHAR2(5)DEFAULT 'true',
    CONSTRAINT FK_ACCESS FOREIGN KEY (username) REFERENCES credentials(username)
    ON DELETE CASCADE
);
    
    
--THIS IS THE ADMIN, needs to be in the db before using the program
INSERT INTO credentials (username,password) VALUES('andy','boss');
INSERT INTO account_access(username,permissions,pending) VALUES('andy','true','false');
COMMIT;

SELECT * FROM credentials;
SELECT * FROM account_access;
SELECT * FROM account;