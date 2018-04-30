-- Create tables

CREATE TABLE Class
(
  classId   NUMBER(2) PRIMARY KEY,
  className VARCHAR2(32) NOT NULL
);
/

CREATE TABLE Location
(
  locationId   NUMBER(2) PRIMARY KEY,
  locationName VARCHAR2(64) NOT NULL
);
/


CREATE TABLE BankAccount
(
  bankAccountId NUMBER(10) PRIMARY KEY,
  gold          NUMBER(8, 2) DEFAULT 0 NOT NULL,
  userId        NUMBER(10)             NOT NULL
);
/


CREATE TABLE JDBCUser
(
  userId            NUMBER(10) PRIMARY KEY,
  userlock          NUMBER(2) DEFAULT -1     NOT NULL,
  username          VARCHAR2(32)             NOT NULL,
  userpass          VARCHAR2(128)            NOT NULL,
  firstName         VARCHAR2(32)             NOT NULL,
  lastName          VARCHAR2(32)             NOT NULL,
  HP                NUMBER(3) DEFAULT 100    NOT NULL,
  LVL               NUMBER(3) DEFAULT 0      NOT NULL,
  EXP               NUMBER(6) DEFAULT 0      NOT NULL,
  ATK               NUMBER(3) DEFAULT 1      NOT NULL,
  DEF               NUMBER(3) DEFAULT 1      NOT NULL,
  gold              NUMBER(8, 2) DEFAULT 100 NOT NULL,
  currentLocationId NUMBER(2) DEFAULT 0      NOT NULL,
  classId           NUMBER(2) DEFAULT 0      NOT NULL
);
/

CREATE TABLE Monster
(
  monsterId   NUMBER(10) PRIMARY KEY,
  monsterName VARCHAR2(32)         NOT NULL,
  HP          NUMBER(3) DEFAULT 50 NOT NULL,
  LVL         NUMBER(3) DEFAULT 0  NOT NULL,
  ATK         NUMBER(3) DEFAULT 1  NOT NULL,
  DEF         NUMBER(3) DEFAULT 1  NOT NULL
);
/

-- Junction Tables

CREATE TABLE Monster_Location
(
  monsterId  NUMBER(10) NOT NULL,
  monsterHP  NUMBER(3)  NOT NULL,
  locationId NUMBER(10) NOT NULL
);

-- Add Administrator

INSERT INTO Class VALUES (0, 'Nothing');
INSERT INTO Class VALUES (1, 'Warrior');
INSERT INTO Class VALUES (2, 'Wizard');
INSERT INTO Class VALUES (3, 'Archer');
INSERT INTO Class VALUES (4, 'Priest/Priestess');

INSERT INTO LOCATION VALUES (0, 'Anywhere');
INSERT INTO LOCATION VALUES (1, 'Home');
INSERT INTO LOCATION VALUES (2, 'Bank');
INSERT INTO LOCATION VALUES (3, 'General Store');
INSERT INTO LOCATION VALUES (4, 'Armor Store');
INSERT INTO LOCATION VALUES (5, 'Weapon Store');
INSERT INTO LOCATION VALUES (6, 'Forest');

INSERT INTO BANKACCOUNT VALUES (0, 999999, 0);

INSERT INTO JDBCUser
VALUES (0, 0, 'admin', 'CBB5650EFFC730AD4BFAE5CFD50E6340', 'Administrator', 'Administrator', 999, 999, 999999, 999, 999,
        999999, 0, 0);

INSERT INTO MONSTER VALUES (0, 'UNBEATABLE MONSTER', 999, 999, 999, 999);
INSERT INTO MONSTER VALUES (1, 'Green Slime I', 10, 0, 1, 1);

INSERT INTO MONSTER_LOCATION VALUES (0, 999, 6);
INSERT INTO MONSTER_LOCATION VALUES (1, 10, 6);

-- Add necessary constraints

-- One to Many
CREATE UNIQUE INDEX Class_className_uindex
  ON Class (className);

-- One to Many
CREATE UNIQUE INDEX Location_locationName_uindex
  ON Location (locationName);

-- One to One
CREATE UNIQUE INDEX BankAccount_userId_uindex
  ON BankAccount (userId);
ALTER TABLE BANKACCOUNT
  ADD CONSTRAINT BANKACCOUNT_USERID_fk
FOREIGN KEY (USERID) REFERENCES JDBCUSER (USERID) ON DELETE CASCADE;

CREATE UNIQUE INDEX JDBCUser_username_uindex
  ON JDBCUser (username);
ALTER TABLE JDBCUSER
  ADD CONSTRAINT JDBCUSER_CLASSID_fk
FOREIGN KEY (CLASSID) REFERENCES CLASS (CLASSID);
ALTER TABLE JDBCUSER
  ADD CONSTRAINT JDBCUSER_LOCATIONID_fk
FOREIGN KEY (CURRENTLOCATIONID) REFERENCES LOCATION (LOCATIONID);

CREATE UNIQUE INDEX MONSTER_MONSTERNAME_uindex
  ON MONSTER (MONSTERNAME);

-- Many to Many
ALTER TABLE MONSTER_LOCATION
  ADD CONSTRAINT MON_LOC_LOCATION_fk
FOREIGN KEY (LOCATIONID) REFERENCES LOCATION (LOCATIONID);
ALTER TABLE MONSTER_LOCATION
  ADD CONSTRAINT MON_LOC_MONSTER_fk
FOREIGN KEY (MONSTERID) REFERENCES MONSTER (MONSTERID);

-- 2 or more sequences

CREATE SEQUENCE ClassId_sequence
  MINVALUE 0
  MAXVALUE 99
  START WITH 5
  INCREMENT BY 1
  NOCACHE;
/

CREATE SEQUENCE LocationId_sequence
  MINVALUE 0
  MAXVALUE 99
  START WITH 7
  INCREMENT BY 1
  NOCACHE;
/

CREATE SEQUENCE BankAccountId_sequence
  MINVALUE 0
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
/

CREATE SEQUENCE JDBCUserId_sequence
  MINVALUE 0
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
/

CREATE SEQUENCE MonsterId_sequence
  MINVALUE 0
  MAXVALUE 9999999999
  START WITH 2
  INCREMENT BY 1
  NOCACHE;
/

-- 2 or more triggers

-- Auto-increment Triggers

CREATE OR REPLACE TRIGGER Class_b_insert
  BEFORE INSERT
  ON Class
  FOR EACH ROW
  BEGIN
    IF :new.classId Is NULL
    THEN
      SELECT classId_sequence.nextval
      INTO :new.classId
      FROM dual;
    end if;
  end;
/

CREATE OR REPLACE TRIGGER Location_b_insert
  BEFORE INSERT
  ON Location
  FOR EACH ROW
  BEGIN
    IF :new.locationId Is NULL
    THEN
      SELECT locationId_sequence.nextval
      INTO :new.locationId
      FROM dual;
    end if;
  end;
/

CREATE OR REPLACE TRIGGER BankAccountId_b_insert
  BEFORE INSERT
  ON BankAccount
  FOR EACH ROW
  BEGIN
    IF :new.bankAccountId Is NULL
    THEN
      SELECT BankAccountId_sequence.nextval
      INTO :new.bankAccountId
      FROM dual;
    end if;
    IF :new.userId Is NULL
    THEN
      SELECT BankAccountId_sequence.currval
      INTO :new.userId
      FROM dual;
    end if;
  end;
/

CREATE OR REPLACE TRIGGER JDBCUser_b_insert
  BEFORE INSERT
  ON JDBCUser
  FOR EACH ROW
  BEGIN
    IF :new.userId Is NULL
    THEN
      SELECT JDBCUserId_sequence.nextval
      INTO :new.userId
      FROM dual;
    end if;
    SELECT GET_USER_HASH(:new.username, :new.userpass)
    INTO :new.userpass
    FROM dual;
  end;
/

CREATE OR REPLACE TRIGGER Monster_b_insert
  BEFORE INSERT
  ON Monster
  FOR EACH ROW
  BEGIN
    IF :new.monsterId Is NULL
    THEN
      SELECT MonsterId_sequence.nextval
      INTO :new.monsterId
      FROM dual;
    end if;
  end;
/

-- 2 or more user defined functions

CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, USERPASS VARCHAR2)
  RETURN VARCHAR2
IS
  EXTRA VARCHAR2(10) := 'SALT';
  BEGIN
    RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
                       INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || USERPASS || EXTRA)));
  END;
/

CREATE OR REPLACE FUNCTION ADD2(num1 NUMBER, num2 NUMBER)
  RETURN NUMBER AS
  BEGIN
    RETURN num1 + num2;
  END;
/

CREATE OR REPLACE FUNCTION SUB2(num1 NUMBER, num2 NUMBER)
  RETURN NUMBER AS
  BEGIN
    RETURN num1 - num2;
  END;
/

-- 3 or more stored procedures

CREATE OR REPLACE PROCEDURE insertClass(newClass IN VARCHAR2)
AS
  BEGIN
    INSERT INTO Class (classId, className) VALUES (null, newClass);
    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE insertLocation(newLocation IN VARCHAR2)
AS
  BEGIN
    INSERT INTO Location (locationId, locationName) VALUES (null, newLocation);
    COMMIT;
  END;
/


CREATE OR REPLACE PROCEDURE insertJDBCUser(newUsername IN VARCHAR2, newUserpass IN VARCHAR2, newFirstName IN VARCHAR2,
                                           newLastName IN VARCHAR2)
AS
  BEGIN
    INSERT INTO JDBCUser (userId, username, userpass, firstName, lastName)
    VALUES (null, newUsername, newUserpass, newFirstName, newLastName);
    INSERT INTO BankAccount (bankAccountId, userId) VALUES (null, null);
    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE insertMonster(newMonsterName IN VARCHAR2, newHP IN NUMBER, newLVL IN NUMBER,
                                          newATK         IN NUMBER,
                                          newDEF         IN NUMBER)
AS
  BEGIN
    INSERT INTO Monster (monsterId, monsterName, HP, LVL, ATK, DEF)
    VALUES (null, newMonsterName, newHP, newLVL, newATK, newDEF);
    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE insertMonLoc(newMonId IN NUMBER, newMonHP IN NUMBER, newLocId IN NUMBER)
AS
  BEGIN
    INSERT INTO Monster_Location VALUES (newMonId, newMonHP, newLocId);
    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE updateJDBCUser(oldUserId IN NUMBER, newFirstName IN VARCHAR2, newLastName IN VARCHAR2, newUserLock IN NUMBER)
AS
  BEGIN
    UPDATE JDBCUser
    SET firstName = newFirstName, lastName = newLastName, userlock = newUserLock
    WHERE userId = oldUserId;
    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE withdrawBankGold(oldUserId IN NUMBER, withdraw IN NUMBER)
AS
  CURSOR c1 IS SELECT gold
               FROM BankAccount
               WHERE userId = oldUserId;
  CURSOR c2 IS SELECT gold
               FROM JDBCUser
               WHERE userId = oldUserId;
  bankGold NUMBER;
  userGold NUMBER;
  BEGIN
    OPEN c1;
    FETCH c1 INTO bankGold;
    UPDATE BankAccount
    SET gold = SUB2(bankGold, withdraw)
    WHERE userId = oldUserId;

    OPEN c2;
    FETCH c2 INTO userGold;
    UPDATE JDBCUser
    SET gold = ADD2(userGold, withdraw)
    WHERE userId = oldUserId;
    COMMIT;
    CLOSE C1;
    CLOSE C2;
  END;
/


CREATE OR REPLACE PROCEDURE depositUserGold(oldUserId IN NUMBER, deposit IN NUMBER)
AS
  CURSOR c1 IS SELECT gold
               FROM BankAccount
               WHERE userId = oldUserId;
  CURSOR c2 IS SELECT gold
               FROM JDBCUser
               WHERE userId = oldUserId;
  bankGold NUMBER;
  userGold NUMBER;
  BEGIN
    OPEN c1;
    FETCH c1 INTO bankGold;
    UPDATE BankAccount
    SET gold = ADD2(bankGold, deposit)
    WHERE userId = oldUserId;

    OPEN c2;
    FETCH c2 INTO userGold;
    UPDATE JDBCUser
    SET gold = SUB2(userGold, deposit)
    WHERE userId = oldUserId;
    COMMIT;
    CLOSE C1;
    CLOSE C2;
  END;
/


-- CREATE OR REPLACE FUNCTION USER_ATK_MON(userId IN NUMBER, monstearId IN NUMBER)
--   RETURN VARCHAR2
-- IS
--   BEGIN
--     --STUFF
--   END;
-- /

-- CREATE OR REPLACE FUNCTION MON_ATK_USER(userId IN NUMBER, monstearId IN NUMBER)
--   RETURN VARCHAR2
-- IS
--   BEGIN
--     --STUFF
--   END;
-- /

-- CREATE OR REPLACE FUNCTION GET_TIME
--   RETURN TIMESTAMP AS timeNow TIMESTAMP;
--   BEGIN
--     SELECT CURRENT_TIMESTAMP
--     INTO timeNow
--     FROM dual;
--     dbms_output.put_line('The current time is ' || timeNow);
--     RETURN timeNow;
--   END;
-- /


-- Test queries
-- SELECT *
-- FROM JDBCUser;
-- SELECT *
-- FROM BankAccount;
-- SELECT *
-- FROM Location;
-- SELECT *
-- FROM Class;
-- SELECT *
-- FROM Monster_Location;
-- SELECT *
-- FROM Monster;
--
-- BEGIN
--   insertJDBCUser('admin', 'admin', 'first1', 'last1');
-- end;
--
-- BEGIN
--   insertLocation('Basement');
-- end;
--
-- BEGIN
--   insertClass('Rouge');
-- end;
--
-- BEGIN
--   insertMonster('Green Slime II', 20, 0, 2, 2);
-- end;
--
-- BEGIN
--   updateJDBCUser(1, 'kokok', 'kokokoko');
-- end;
--
-- BEGIN
--   withdrawBankGold(1, .75);
-- end;
--
-- BEGIN
--   depositUserGold(1, 1.75);
-- end;