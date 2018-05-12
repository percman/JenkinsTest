--Create a user for the database connection


--Grant the appropriate privileges to the user
--Removed Connection Information because this version is on GitHub
GRANT CONNECT TO ;
GRANT CREATE ANY TABLE TO ;
GRANT INSERT ANY TABLE TO ;
GRANT UPDATE ANY TABLE TO ;
GRANT DELETE ANY TABLE TO ;
GRANT CREATE ANY PROCEDURE TO ;
GRANT CREATE ANY TRIGGER TO ;
GRANT CREATE ANY SEQUENCE TO ;
GRANT UNLIMITED TABLESPACE TO ;

--Inside the Extensive_Example connection
--Create a USER table
CREATE TABLE pokemon_user (
id NUMBER(10),
username VARCHAR2(30),
password VARCHAR2(100),
firstname VARCHAR2(45),
lastname VARCHAR2(45),
CONSTRAINT PK_USER PRIMARY KEY (id),
CONSTRAINT UK_USERNAME UNIQUE (username)
);

--Create a sequence which will automatically increment the primary key for us
CREATE SEQUENCE pokemon_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD 
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

--Create a before insert trigger that will autoincrement the PK and hash the password
CREATE OR REPLACE TRIGGER pokemon_user_b_insert 
BEFORE INSERT
ON pokemon_user
FOR EACH ROW
BEGIN
    IF :new.id IS NULL THEN 
        SELECT pokemon_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/

--Create a stored procedure to insert new users
CREATE OR REPLACE PROCEDURE insert_user(new_username IN VARCHAR2, new_password IN VARCHAR2, 
                            new_firstname IN VARCHAR2, new_lastname IN VARCHAR2)
AS
BEGIN
    INSERT INTO pokemon_user (username, password, firstname, lastname, id)
    VALUES (new_username, new_password, new_firstname, new_lastname, null);
    COMMIT;
END;
/
    
--Create a pokemon table
CREATE TABLE pokemon (
id NUMBER(10),
name VARCHAR2(40),
type VARCHAR2(40),
CONSTRAINT PK_POKEMON PRIMARY KEY (id)
);

--Create a sequence for the id sequence
CREATE SEQUENCE id_sequence
    START WITH 1
    INCREMENT BY 1;
                            
--Create a trigger to handle the auto incrementing PK         
CREATE OR REPLACE TRIGGER pokemon_b_insert
BEFORE INSERT
ON pokemon 
FOR EACH ROW 
BEGIN
    IF :new.id IS NULL THEN 
        SELECT id_sequence.nextval INTO :new.id FROM dual;
    END IF;
END;
/

--Create a procedure to insert pokemon
CREATE OR REPLACE PROCEDURE insert_pokemon (new_name IN VARCHAR2, new_type IN VARCHAR2)
AS
BEGIN
    INSERT INTO pokemon VALUES(null, new_name, new_type);
    COMMIT;
END;
/


--Create the junction table, called junction_table bc we messed up originally
CREATE TABLE junction_table (
pokemon_id NUMBER(10),
user_id NUMBER(10),
CONSTRAINT PK_JUNCTION_TABLE PRIMARY KEY (pokemon_id, user_id),
CONSTRAINT FK_POKEMON FOREIGN KEY (pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES pokemon_user (id) ON DELETE CASCADE
);
        
        
--Create a procedure that will add a pokemon to a trainer 
CREATE OR REPLACE PROCEDURE add_pokemon_to_trainer(pokemon_name IN VARCHAR2, user_name IN VARCHAR2)
AS
    /* Declaration Section */
    pokemon_id NUMBER(10);
    user_id NUMBER(10);
BEGIN
    SELECT id INTO pokemon_id FROM pokemon WHERE name = pokemon_name;
    SELECT id INTO user_id FROM pokemon_user WHERE username = user_name;
    INSERT INTO junction_table VALUES (pokemon_id, user_id);
    COMMIT;
END;
/