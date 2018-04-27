-- This was in our administrative connection
--------------------------------------------

-- Create a user for the database connection
CREATE USER pokemon_admin IDENTIFIED BY my_password;

-- Grant the appropriate privileges to the user
GRANT CONNECT TO pokemon_admin;
GRANT CREATE ANY TABLE TO pokemon_admin;
GRANT INSERT ANY TABLE TO pokemon_admin;
GRANT UPDATE ANY TABLE TO pokemon_admin;
GRANT DELETE ANY TABLE TO pokemon_admin;
GRANT CREATE ANY PROCEDURE TO pokemon_admin;
GRANT CREATE ANY TRIGGER TO pokemon_admin;
GRANT CREATE ANY SEQUENCE TO pokemon_admin;
GRANT UNLIMITED TABLESPACE TO pokemon_admin;


-- Inside the Pokemon_Admin connection
--------------------------------------

-- Create a user table
CREATE TABLE poke_user (
    id NUMBER(10),
    username VARCHAR2(30),
    password VARCHAR2(100),
    firstname VARCHAR2(45),
    lastname VARCHAR2(45),
    CONSTRAINT PK_USER PRIMARY KEY (id),
    CONSTRAINT UK_USERNAME UNIQUE (username)
);


-- Create a sequence which will automatically increment the primary key for us
CREATE SEQUENCE pokemon_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
 
 
-- Hashing function that combines username, password, and a special word    
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/


-- Create a before insert trigger that will autoincrement the PK and hash the password
CREATE OR REPLACE TRIGGER pokemon_user_b_insert
BEFORE INSERT
ON poke_user
FOR EACH ROW
BEGIN
    IF :new.id IS NULL THEN
        SELECT pokemon_id_sequence.nextval INTO :new.id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.username, :new.password) INTO :new.password FROM dual;
END;
/


-- Create a stored procedure to insert new users
CREATE OR REPLACE PROCEDURE insert_user (new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                         new_firstname IN VARCHAR2, new_lastname IN VARCHAR2)
AS
BEGIN
    INSERT INTO poke_user (username, password, firstname, lastname, id)
        VALUES (new_username, new_password, new_firstname, new_lastname, null);
        COMMIT;
END;
/


-- Create a pokemon table
CREATE TABLE pokemon (
    id NUMBER(10),
    name VARCHAR2(40),
    type VARCHAR2(40),
    CONSTRAINT PK_POKEMON PRIMARY KEY (id)
);


-- Create a sequence for the id sequnce
CREATE SEQUENCE id_sequence
    START WITH 1
    INCREMENT BY 1;


-- Create a trigger to handle the auto-incrementing PK
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


-- Create a procedure to insert pokemon
CREATE OR REPLACE PROCEDURE insert_pokemon (new_name IN VARCHAR2, new_type IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO pokemon VALUES (null, new_name, new_type);
        COMMIT;
    END;
    /


-- Create the junction table, called junction_table because William messed up originally
CREATE TABLE junction_table (
    pokemon_id NUMBER(10),
    user_id NUMBER(10),
    CONSTRAINT PK_JUNCTION_TABLE PRIMARY KEY (pokemon_id, user_id),
    CONSTRAINT FK_POKEMON FOREIGN KEY (pokemon_id) 
        REFERENCES pokemon (id) 
        ON DELETE CASCADE,
    CONSTRAINT FK_USER FOREIGN KEY (user_id)
        REFERENCES poke_user (id)
        ON DELETE CASCADE
    );


-- Create a procedure that will add a pokemon to a trainer
CREATE OR REPLACE PROCEDURE add_pokemon_to_trainer (pokemon_name IN VARCHAR2, user_name IN VARCHAR2)
    AS
        /* Declaration Section */
        pokemon_id NUMBER(10);
        user_id NUMBER(10);
    BEGIN
        SELECT id INTO pokemon_id FROM pokemon 
            WHERE name = pokemon_name;
        SELECT id INTO user_id FROM poke_user
            WHERE username = user_name;
        INSERT INTO junction_table VALUES (pokemon_id, user_id);
        COMMIT;
    END;
    /








