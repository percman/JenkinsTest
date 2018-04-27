/********************************************************************
Run the following lines with administrative access to your database
*********************************************************************/

CREATE USER jdbcbank_user IDENTIFIED BY jdbc_password;
GRANT CONNECT TO jdbcbank_user;