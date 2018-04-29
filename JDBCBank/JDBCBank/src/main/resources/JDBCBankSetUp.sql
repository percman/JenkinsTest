/********************************************************************
Run the following lines with administrative access to your database
*********************************************************************/

CREATE USER jdbcbank_user IDENTIFIED BY jdbcbank_password;
GRANT CONNECT TO jdbcbank_user;
GRANT CONNECT TO jdbcbank_user;
GRANT CREATE ANY TABLE TO jdbcbank_user;
GRANT UPDATE ANY TABLE TO jdbcbank_user;
GRANT DELETE ANY TABLE TO jdbcbank_user;
GRANT INSERT ANY TABLE TO jdbcbank_user;
GRANT UNLIMITED TABLESPACE TO jdbcbank_user;
GRANT DROP ANY TABLE TO jdbcbank_user;
GRANT CREATE ANY TRIGGER TO jdbcbank_user;
GRANT CREATE ANY SEQUENCE TO jdbcbank_user;
GRANT CREATE ANY PROCEDURE TO jdbcbank_user;


/*************************************************************
Run the following code in the new jdbcbank_user connection
**************************************************************/

-- Drop tables if they have been created before
DROP TABLE principal;
DROP TABLE teacher;
DROP TABLE student;

-- Create the necessary tables
CREATE TABLE principal (
    p_id NUMBER (1), 
    p_username VARCHAR2 (45),
    p_password VARCHAR2 (100),
    p_firstname VARCHAR2 (45),
    p_lastname VARCHAR2 (45),
    CONSTRAINT PK_P_ID PRIMARY KEY (p_id),
    CONSTRAINT CK_P_ID CHECK (p_id = 1)
);

CREATE TABLE teacher (
    t_id NUMBER (10), 
    t_username VARCHAR2 (45),
    t_password VARCHAR2 (100),
    t_firstname VARCHAR2 (45),
    t_lastname VARCHAR2 (45),
    t_approved NUMBER (1),
    t_locked NUMBER (1),
    CONSTRAINT PK_T_ID PRIMARY KEY (t_id),
    CONSTRAINT UK_T_USERNAME UNIQUE (t_username),
    CONSTRAINT CK_T_APPROVED CHECK (t_approved = 0 OR t_approved = 1),
    CONSTRAINT CK_T_LOCKED CHECK (t_locked = 0 OR t_locked = 1)
);

CREATE TABLE student (
    s_id NUMBER (10), 
    s_username VARCHAR2 (45),
    s_password VARCHAR2 (100),
    s_firstname VARCHAR2 (45),
    s_lastname VARCHAR2 (45),
    s_coins NUMBER (10),
    s_approved NUMBER (1),
    s_locked NUMBER (1),
    s_bought_sub NUMBER (1),
    s_bought_mult NUMBER (1),
    s_bought_div NUMBER (1),
    CONSTRAINT PK_S_ID PRIMARY KEY (s_id),
    CONSTRAINT UK_S_USERNAME UNIQUE (s_username),
    CONSTRAINT CK_S_COINS CHECK (s_coins >= 0),
    CONSTRAINT CK_S_APPROVED CHECK (s_approved = 0 OR s_approved = 1),
    CONSTRAINT CK_S_LOCKED CHECK (s_locked = 0 OR s_locked = 1),
    CONSTRAINT CK_S_BOUGHT_SUB CHECK (s_bought_sub = 0 OR s_bought_sub = 1),
    CONSTRAINT CK_S_BOUGHT_MULT CHECK (s_bought_mult = 0 OR s_bought_mult = 1),
    CONSTRAINT CK_S_BOUGHT_DIV CHECK (s_bought_div = 0 OR s_bought_div = 1)
);

CREATE TABLE username (
    username VARCHAR2 (45),
    user_type VARCHAR2 (9),
    CONSTRAINT UK_USERNAME UNIQUE (username),
    CONSTRAINT CK_USER_TYPE CHECK (user_type = 'student'
                                OR user_type = 'teacher'
                                OR user_type = 'principal')
);


-- Create the sequences for teacher_id and student_id
-- principal_id does not need a sequence because there is only one principal
CREATE SEQUENCE teacher_id_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE student_id_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- Hashing function that combines username, password, and a special word    
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) 
                                            RETURN VARCHAR2
    IS
        EXTRA VARCHAR2(10) := 'MATH';
    BEGIN
        RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
        INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
    END;
    /


-- Create triggers for before inserting into the teacher and student tables
CREATE OR REPLACE TRIGGER principal_b_insert
    BEFORE INSERT
    ON principal
    FOR EACH ROW
    BEGIN
        SELECT GET_USER_HASH(:new.p_username, :new.p_password) 
            INTO :new.p_password FROM dual;
    END;
    /
    
CREATE OR REPLACE TRIGGER teacher_b_insert
    BEFORE INSERT
    ON teacher
    FOR EACH ROW
    BEGIN
        IF :new.t_id IS NULL THEN
            SELECT teacher_id_sequence.nextval INTO :new.t_id FROM dual;
        END IF;
        SELECT GET_USER_HASH(:new.t_username, :new.t_password) 
            INTO :new.t_password FROM dual;
    END;
    /

CREATE OR REPLACE TRIGGER student_b_insert
    BEFORE INSERT
    ON student
    FOR EACH ROW
    BEGIN
        IF :new.s_id IS NULL THEN
            SELECT student_id_sequence.nextval INTO :new.s_id FROM dual;
        END IF;
        SELECT GET_USER_HASH(:new.s_username, :new.s_password) 
            INTO :new.s_password FROM dual;
    END;
    /


-- Create stored procedures to insert a principal, teachers, and students
CREATE OR REPLACE PROCEDURE insert_principal (new_username IN VARCHAR2, new_password IN VARCHAR2,
                                                firstname IN VARCHAR2, lastname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO principal (p_id, p_username, p_password, p_firstname, p_lastname)
            VALUES (1, new_username, new_password, firstname, lastname);
        COMMIT;
    END;
    /

CREATE OR REPLACE PROCEDURE insert_teacher (new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                            firstname IN VARCHAR2, lastname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO teacher (t_id, t_username, t_password, t_firstname, t_lastname, 
                                t_approved, t_locked)
            VALUES (null, new_username, new_password, firstname, lastname, 0, 0);
        COMMIT;
    END;
    /
    
CREATE OR REPLACE PROCEDURE insert_student (new_username IN VARCHAR2, new_password IN VARCHAR2, 
                                            firstname IN VARCHAR2, lastname IN VARCHAR2)
    AS
    BEGIN
        INSERT INTO student (s_id, s_username, s_password, s_firstname, s_lastname, 
                                s_coins, s_approved, s_locked, s_bought_sub, 
                                s_bought_mult, s_bought_div)
            VALUES (null, new_username, new_password, firstname, lastname, 0, 0, 0, 0, 0, 0);
        COMMIT;
    END;
    /

-- Create a function that will update the principal, teacher, and student
CREATE OR REPLACE PROCEDURE update_principal(new_username IN VARCHAR2, new_password IN VARCHAR2, firstname IN VARCHAR2,
                                            lastname IN VARCHAR2)
    AS
    BEGIN
        UPDATE principal SET
            p_password = get_user_hash(new_username, new_password),
            p_firstname = firstname,
            p_lastname = lastname
        WHERE p_username = new_username;
        COMMIT;

    END;
    /

CREATE OR REPLACE PROCEDURE update_teacher(new_username IN VARCHAR2, new_password IN VARCHAR2, firstname IN VARCHAR2,
                                            lastname IN VARCHAR2)
    AS
    BEGIN
        UPDATE teacher SET
            t_password = get_user_hash(new_username, new_password),
            t_firstname = firstname,
            t_lastname = lastname
        WHERE t_username = new_username;
        COMMIT;

    END;
    /

CREATE OR REPLACE PROCEDURE update_student(new_username IN VARCHAR2, new_password IN VARCHAR2, firstname IN VARCHAR2,
                                            lastname IN VARCHAR2, coins IN NUMBER, bought_sub IN NUMBER,
                                            bought_mult IN NUMBER, bought_div IN NUMBER)
    AS
    BEGIN
        UPDATE student SET 
            s_password = get_user_hash(new_username, new_password),
            s_firstname = firstname,
            s_lastname = lastname,
            s_coins = coins,
            s_bought_sub = bought_sub,
            s_bought_mult = bought_mult,
            s_bought_div = bought_div
        WHERE s_username = new_username;
        COMMIT;
    END;
    /

-- Create a function that will return 1 if there is already a principal
CREATE OR REPLACE FUNCTION get_principal RETURN NUMBER
    AS
        p_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO p_exists FROM principal;
    RETURN p_exists;
    END;
    /

SELECT * FROM student;
BEGIN
    insert_student('sUsername', 'sPassword', 'sFirstname', 'sLastname');
END;
/

UPDATE student SET s_approved = 1;