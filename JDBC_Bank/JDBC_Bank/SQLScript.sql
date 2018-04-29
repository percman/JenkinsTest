--Creation of a new user and security group management
CREATE USER my_JDBC IDENTIFIED BY bankPassword;
GRANT CONNECT TO my_JDBC ;
GRANT CREATE ANY TABLE TO my_JDBC;
GRANT INSERT ANY TABLE TO my_JDBC;
GRANT UPDATE ANY TABLE TO my_JDBC;
GRANT ALTER ANY TABLE TO my_JDBC;
GRANT DELETE ANY TABLE TO my_JDBC;
GRANT CREATE ANY PROCEDURE TO my_JDBC;
GRANT CREATE ANY TRIGGER TO my_JDBC;
GRANT CREATE ANY SEQUENCE TO my_JDBC;
GRANT UNLIMITED TABLESPACE TO my_JDBC;

--creation of the user table
CREATE TABLE user_table(
    user_id NUMBER(10) NOT NULL,
    user_firstname VARCHAR2(50) NOT NULL,
    user_lastname VARCHAR2(50) NOT NULL,
    user_password VARCHAR2(150) NOT NULL,
    user_approved NUMBER(1) NOT NULL,
    user_locked NUMBER (1) NOT NULL,
    CONSTRAINT approved_user CHECK (user_approved = 0 OR user_approved = 1),
    CONSTRAINT locked_user CHECK (user_locked = 0 OR user_locked = 1),
    CONSTRAINT user_pk PRIMARY KEY(user_id)
);

--creation of the product table
CREATE TABLE product(
    product_id NUMBER(10) NOT NULL,
    product_name VARCHAR2(50) NOT NULL,
    product_price NUMBER(10) NOT NULL,
    product_quantity NUMBER(10),
    CONSTRAINT product_pk PRIMARY KEY(product_id)
);

--creation of the user and product junction table
CREATE TABLE user_product_junction(
    cart_id NUMBER(10) NOT NULL,
    user_id,
    product_id ,
    CONSTRAINT cart_pk PRIMARY KEY(cart_id, user_id, product_id),
    CONSTRAINT FK_USER FOREIGN KEY(user_id) REFERENCES user_table (user_id) ON DELETE CASCADE,   
    CONSTRAINT FK_PRODUCT FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = "user_table";
 --creation of a sequence to auto-increment user id values
CREATE SEQUENCE user_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
 --creation of a sequence to auto-increment product id values
CREATE SEQUENCE product_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

 --creation of a sequence to auto-increment user_product_junction id values
CREATE SEQUENCE user_product_id_sequence
    MINVALUE 0
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
--hashcode for password encryption  
CREATE OR REPLACE FUNCTION GET_USER_HASH(firstname VARCHAR,  password VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA =>FIRSTNAME|| PASSWORD || EXTRA)));
END;
/

--create a before insert trigger that will autoincrement the pk and hash the password of a user
CREATE OR REPLACE TRIGGER user_b_insert
BEFORE INSERT
ON user_table
FOR EACH ROW
BEGIN
    IF :new.user_id IS NULL THEN
    SELECT user_id_sequence.nextval INTO :new.user_id FROM dual;
    END IF;
    SELECT GET_USER_HASH(:new.user_firstname, :new.user_password)
    INTO :new.user_password FROM dual;
END;
/
DELETE FROM user_table;
COMMIT;

--creation of a trigger that will autoincrement the product table
CREATE OR REPLACE TRIGGER product_b_insert
BEFORE INSERT
ON product
FOR EACH ROW
BEGIN
    IF :new.product_id IS NULL THEN
    SELECT product_id_sequence.nextval INTO :new.product_id FROM dual;
    END IF;
END;
/

--creation of a trigger that will autoincrement the user_product_junction table
CREATE OR REPLACE TRIGGER user_product_b_insert
BEFORE INSERT
ON user_product_junction
FOR EACH ROW
BEGIN
    IF :new.cart_id IS NULL THEN
    SELECT user_product_id_sequence.nextval INTO :new.cart_id FROM dual;
    END IF; 
END;
/

--used to  insert users into the user_table
CREATE OR REPLACE PROCEDURE insert_user
(new_user_firstname IN VARCHAR2, new_user_lastname IN VARCHAR2, new_user_password IN VARCHAR2, 
new_user_approved IN NUMBER, new_user_locked IN NUMBER )
AS
BEGIN
    INSERT INTO user_table 
    VALUES(NULL, new_user_firstname, new_user_lastname, new_user_password, 0, 0);
    COMMIT;
END;
/

--used to  insert products into the product_table
CREATE OR REPLACE PROCEDURE insert_product
( new_product_name IN VARCHAR2, new_product_price IN NUMBER, new_product_quantity IN NUMBER)
AS
BEGIN
    INSERT INTO product VALUES(NULL, new_product_name, new_product_price, new_product_quantity);
    COMMIT;
END;
/

--used to  inserts users and products into the user_product_junction table
CREATE OR REPLACE PROCEDURE insert_junction
(new_user_id IN NUMBER, new_product_id IN NUMBER)
AS
BEGIN
    INSERT INTO user_product_junction VALUES(NULL ,new_user_id, new_product_id);
    COMMIT;
END;
/


