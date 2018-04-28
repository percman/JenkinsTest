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
