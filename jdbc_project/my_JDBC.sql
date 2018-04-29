--creation of the user table
CREATE TABLE user_table(
    user_id NUMBER(10) NOT NULL,
    user_firstname VARCHAR2(50) NOT NULL,
    user_lastname VARCHAR2(50) NOT NULL,
    user_password VARCHAR2(150) NOT NULL,
    user_approved NUMBER(1) NOT NULL,
    user_locked NUMBER (1) NOT NULL,
    CONSTRAINT approved_user CHECK (user_approved IN  (1,0)),
    CONSTRAINT locked_user CHECK (user_locked IN  (1,0)),
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
CREATE OR REPLACE FUNCTION GET_USER_HASH( password VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => PASSWORD || EXTRA)));
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
    SELECT GET_USER_HASH(:new.user_password) INTO :new.user_password FROM dual;
END;
/

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
(new_user_id NUMBER, new_user_firstname VARCHAR2, new_user_lastname VARCHAR2, new_user_password VARCHAR2, new_user_approved NUMBER, new_user_locked NUMBER)
AS
BEGIN
    INSERT INTO user_table VALUES(NULL , new_user_firstname, new_user_lastname, new_user_password, 0, 0);
    COMMIT;
END;
/

--used to  insert products into the product_table
CREATE OR REPLACE PROCEDURE insert_product
(new_product_id NUMBER , new_product_name VARCHAR2, new_product_price NUMBER, new_product_quantity NUMBER)
AS
BEGIN
    INSERT INTO producT VALUES(NULL , new_product_name, new_product_price, new_product_quantity);
    COMMIT;
END;
/

--used to  inserts users and products into the user_product_junction table
CREATE OR REPLACE PROCEDURE insert_junction
(new_cart_id NUMBER , new_user_id NUMBER, new_product_id NUMBER)
AS
BEGIN
    INSERT INTO user_product_junction VALUES(NULL ,NULL, NULL);
    COMMIT;
END;
/

