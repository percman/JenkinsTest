CREATE TABLE movie_user(
user_username VARCHAR2(30),
user_password VARCHAR2(100),
user_id     NUMBER,
locked NUMBER(1) DEFAULT 0,
approved NUMBER(1) DEFAULT 0, 
CONSTRAINT ckUserLocked check (locked in (0,1)),
CONSTRAINT ckUserApproved check (approved in (0,1)),
CONSTRAINT uk_user_username UNIQUE (user_username),
CONSTRAINT pk_user_id PRIMARY KEY(user_id));

CREATE TABLE movie_admin(
admin_username VARCHAR2(30),
admin_password VARCHAR2(100),
CONSTRAINT pk_admin_username PRIMARY KEY(admin_username));

CREATE TABLE movie_renting(
title VARCHAR(60),
movie_id NUMBER(10),
CONSTRAINT pk_movie_id PRIMARY KEY(movie_id),
CONSTRAINT uk_movie_title UNIQUE(title));

CREATE TABLE user_movie(
movie_id NUMBER(10),
user_id NUMBER(10),
CONSTRAINT PK_junction_table PRIMARY KEY(movie_id,user_id),
CONSTRAINT FK_movie FOREIGN KEY(movie_id) REFERENCES movie_renting(movie_id) ON DELETE CASCADE,
CONSTRAINT FK_user FOREIGN KEY(user_id) REFERENCES movie_user(user_id) ON DELETE CASCADE);


CREATE OR REPLACE PROCEDURE insert_user (new_name IN VARCHAR2, new_pass IN VARCHAR2)
AS
BEGIN
    INSERT INTO movie_user(USER_USERNAME,USER_PASSWORD,USER_ID) VALUES(new_name,new_pass,NULL);
END;
/

CREATE OR REPLACE PROCEDURE insert_admin (new_name IN VARCHAR2, new_pass IN VARCHAR2)
AS
BEGIN
    INSERT INTO movie_admin VALUES(new_name,new_pass);
END;
/

CREATE OR REPLACE PROCEDURE lock_user (username IN VARCHAR2)
AS
BEGIN
    UPDATE movie_user SET locked = 1 WHERE USER_USERNAME = username;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE unlock_user (username IN VARCHAR2)
AS
BEGIN
    UPDATE movie_user SET locked = 0 WHERE USER_USERNAME = username;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE approve_user (username IN VARCHAR2)
AS
BEGIN
    UPDATE movie_user SET approved = 1 WHERE USER_USERNAME = username;
    COMMIT;
END;
/

CREATE SEQUENCE user_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER user_insert
BEFORE INSERT 
ON movie_user
FOR EACH ROW 
BEGIN
    IF :new.user_id IS NULL THEN
    SELECT user_id_sequence.nextval INTO : new.user_id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:NEW.user_username, :new.user_password) INTO :new.user_password FROM dual;
END;
/

CREATE OR REPLACE TRIGGER admin_insert
BEFORE INSERT 
ON movie_admin
FOR EACH ROW 
BEGIN
    SELECT GET_USER_HASH(:NEW.admin_username, :new.admin_password) INTO :new.admin_password FROM dual;
END;
/

CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

CREATE OR REPLACE PROCEDURE insert_movie (new_title IN VARCHAR2)
AS
BEGIN
    INSERT INTO movie_renting(title) VALUES(new_title);
END;
/

CREATE SEQUENCE movie_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;
    
    
CREATE OR REPLACE TRIGGER movie_insert
BEFORE INSERT 
ON movie_renting
FOR EACH ROW 
BEGIN
    IF :new.movie_id IS NULL THEN
    SELECT movie_id_sequence.nextval INTO : new.movie_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE rent_user_movie(user_name IN VARCHAR2, movie_title IN VARCHAR2)
AS
    new_movie_id NUMBER(10);
    new_user_id NUMBER(10);
BEGIN
    SELECT movie_id INTO new_movie_id FROM movie_renting WHERE title = movie_title;
    SELECT user_id INTO new_user_id FROM movie_user WHERE user_username = user_name;
    INSERT INTO user_movie VALUES (new_movie_id,new_user_id);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE return_user_movie(user_name IN VARCHAR2, movie_title IN VARCHAR2)
AS
    new_movie_id NUMBER(10);
    new_user_id NUMBER(10);
BEGIN
    SELECT movie_id INTO new_movie_id FROM movie_renting WHERE title = movie_title;
    SELECT user_id INTO new_user_id FROM movie_user WHERE user_username = user_name;
    Delete from user_movie WHERE movie_id = new_movie_id AND user_id = new_user_id;
    COMMIT;
END;
/

Begin
return_user_movie('megan','Superman');
End;
/

