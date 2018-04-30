drop table project_transaction cascade constraints purge;
drop table project_user cascade constraints purge;
drop table project_administrator cascade constraints purge;
drop sequence user_primary_key;
drop sequence administrator_primary_key;
drop sequence transaction_primary_key;

create table project_administrator(
    administratorid number not null,
    name varchar2(100) not null,
    password varchar2(100) not null,
    constraint pk_administrator primary key(administratorid)
);
 
 create table project_user (
    userid number not null,
    name varchar(100) not null,
    password varchar2(100) not null,
    approveadminid number,
    rejectadminid number,
    is_locked number,
    constraint pk_user primary key(userid),
    constraint fk_approveadmin 
        foreign key (approveadminid)
        references project_administrator(administratorid),
    constraint fk_rejectadmimin
        foreign key (rejectadminid)
        references project_administrator(administratorid)
);

create table project_transaction(
    transactionid number not null,
    userid number not null,
    amount number not null,
    constraint pk_transaction primary key(transactionid)
);

create sequence user_primary_key
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence administrator_primary_key
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence transaction_primary_key
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;
    
create or replace function name_password_hash
    (in_user varchar2, in_password varchar2) return varchar2
is
extra varchar2(10) := 'pepper';
begin
    return to_char(DBMS_OBFUSCATION_TOOLKIT.MD5(
        input => UTL_I18N.STRING_TO_RAW(data => in_user || in_password || extra)));
end;
/

create or replace function read_user_balance(in_name varchar2) return number
is
    return_balance number;
begin
    select sum(t.amount) into return_balance from project_transaction t, project_user u where u.name=in_name and u.userid=t.transactionid;
    return return_balance;
end;
/

create or replace trigger before_create_administrator
before insert
on project_administrator
for each row
begin
    if :new.administratorid is null then
        select administrator_primary_key.nextval into :new.administratorid from dual;
    end if;
    select name_password_hash(:new.name, :new.password) into :new.password from dual;
end;
/

create or replace trigger before_create_user
before insert
on project_user
for each row
begin
    if :new.userid is null then
        select user_primary_key.nextval into :new.userid from dual;
    end if;
    select name_password_hash(:new.name, :new.password) into :new.password from dual;
end;
/

create or replace trigger before_create_transaction
before insert
on project_transaction
for each row
begin
    if :new.transactionid is null then
        select transaction_primary_key.nextval into :new.transactionid from dual;
    end if;
end;
/

create or replace procedure create_administrator
    (in_name in varchar2, in_password in varchar2)
as
begin
    insert into project_administrator (administratorid, name, password) 
        values (null, in_name, in_password);
end;
/

create or replace procedure create_user
    (in_name in varchar2, in_password in varchar2)
as 
    calculated_hash varchar(100);
begin
    insert into project_user (userid, approveadminid, rejectadminid, name, password, is_locked)
        values (null, null, null, in_name, in_password, 0);
end;
/

create or replace procedure create_transaction
    (in_userid in number, in_amount in number)
as
begin
    insert into project_transaction(transactionid, userid, amount)
        values(null, in_userid, in_amount);
end;
/

create or replace procedure lock_user
    (in_name in varchar2)
as
begin
    update project_user
    set is_locked=1
    where name=in_name;
end;
/

create or replace procedure unlock_user
    (in_name in varchar2)
as
begin
    update project_user
    set is_locked=0
    where name=in_name;
end;
/
--	create or replace procedure reject_user(in_user_name, in_admin_name)
--	create or replace procedure approve_user(in_user_name, in_admin_name)
create or replace procedure reject_user
    (in_user_name varchar2, in_admin_name varchar2)
as
    administrator_id number;
begin
    select a.administratorid into administrator_id from project_user u, project_administrator a where name=in_admin_name;
    update project_user
    set approveadminid=administrator_id;
end;
/

begin
    create_administrator('andrew', 'password');
    create_user('andy', 'password');
    create_user('vince', 'password');
    create_user('cameron', 'password');
    create_transaction(0, 0);
    create_transaction(1, 20);
    create_transaction(2, 40);
end;
/

select * from project_transaction;
select * from project_user;
select * from project_administrator;