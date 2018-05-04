Database Summary
===========================================================================

Tables
===========================================================================
project_administrator(administratorid, name, password)
project_transaction(transactionid, userid, amount)
project_user(userid, name, password, approveadminid, rejectadminid, is_locked)

Procedures
===========================================================================

administrator procedures
--------------------------------------------------------------------------------
create_administrator(in_name, in_password)
approve_user(in_user_name, in_admin_name)
reject_user(in_user_name, in_admin_name)
lock_user(in_name)
unlock_user(in_name)

user procedures
--------------------------------------------------------------------------------
create_transaction(in_name, in_amount)
create_user(in_name, in_password)

Functions
--------------------------------------------------------------------------------
read_user_balance(in_name) returns number