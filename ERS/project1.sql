--drop table managers cascade constraints purge;
--drop table employees cascade constraints purge;
--drop table information cascade constraints purge;
--drop table reimbursement cascade constraints purge;
--drop sequence pk_managers;
--drop sequence pk_employees;
--drop sequence pk_information;
--drop sequence pk_reimbursement;

drop table employee cascade constraints purge;
drop table manager cascade constraints purge;
drop table information cascade constraints purge;
drop table reimbursement cascade constraints purge;
drop sequence pks_employee;
drop sequence pks_information;
drop sequence pks_manager;
drop sequence pks_reimbursement;

create table employee(
    employeeid number,
    managerid number,
    username varchar2(100) not null,
    password varchar2(100) not null,
    constraint pk_employeeid primary key(employeeid)
);

create table manager(
    managerid number,
    employeeid number,
    constraint pk_managerid primary key(managerid),
    constraint fk__manager_employeeid foreign key(employeeid) references employee
);

alter table employee
    add constraint fk_employee_managerid foreign key(managerid) references manager;

create table information(
    informationid number,
    employeeid number,
    firstname varchar2(100),
    middlename varchar2(100),
    lastname varchar2(100),
    constraint pk_informationid primary key(informationid),
    constraint fk_information_employeeid foreign key(employeeid) references employee
);

create table reimbursement(
    reimbursementid number,
    employeeid number,
    status varchar2(100) not null,
    image varchar2(100),
    category varchar2(100),
    constraint pk_reimbursementid primary key(reimbursementid),
    constraint fk_reimbursement_employeeid foreign key(employeeid) references employee(employeeid)
);

create sequence pks_employee
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence pks_information
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence pks_manager
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence pks_reimbursement
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create or replace function hash
    (in_username varchar2, in_password varchar2) return varchar2
is
extra varchar2(10) := 'pepper';
begin
    return to_char(DBMS_OBFUSCATION_TOOLKIT.MD5(
        input => UTL_I18N.STRING_TO_RAW(data => in_username || in_password || extra)));
end;
/

create or replace trigger before_create_employee
before insert
on employee
for each row
begin
    if :new.employeeid is null then
        select pks_employee.nextval into :new.employeeid from dual;
    end if;
    select hash(:new.username, :new.password) into :new.password from dual;
end;
/

create or replace trigger before_create_information
before insert
on information
for each row
begin
    if :new.informationid is null then
        select pks_information.nextval into :new.informationid from dual;
    end if;
end;
/

create or replace trigger before_create_manager
before insert
on manager
for each row
begin
    if :new.managerid is null then
        select pks_manager.nextval into :new.managerid from dual;
    end if;
end;
/

create or replace trigger before_create_reimbursement
before insert
on reimbursement
for each row
begin
    if :new.reimbursementid is null then
        select pks_reimbursement.nextval into :new.reimbursementid from dual;
    end if;
end;
/

create or replace procedure create_employee
    (in_managerid number, in_username varchar2, in_password varchar2)
as
begin
    insert into employee(employeeid, managerid, username, password)
        values(null, in_managerid, in_username, in_password);
    commit;
end;
/

create or replace procedure create_information
    (in_employeeid number, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
begin
    insert into information(informationid, employeeid, firstname, middlename, lastname)
        values(null, in_employeeid, in_firstname, in_middlename, in_lastname);
    commit;
end;
/

create or replace procedure create_manager
    (in_employeeid number)
as
begin
    insert into manager(managerid, employeeid)
        values(null, in_employeeid);
    commit;
end;
/

create or replace procedure create_reimbursement
    (in_employeeid number, in_status varchar2, in_image varchar2, in_category varchar2)
as
begin
    insert into reimbursement(reimbursementid, employeeid, status, image, category)
        values(null, in_employeeid, in_status, in_image, in_category);
    commit;
end;
/

create or replace function read_employee
    (in_employeeid number) return employee%rowtype
as
    return_row employee%rowtype;
begin
    select * into return_row from employee where employeeid=in_employeeid;
    return return_row;
end;
/

create or replace function read_information
    (in_informationid number) return information%rowtype
as
    return_row information%rowtype;
begin
    select * into return_row from information where informationid=in_informationid;
    return return_row;
end;
/

create or replace function read_manager
    (in_managerid number) return manager%rowtype
as
    return_row manager%rowtype;
begin
    select * into return_row from manager where managerid=in_managerid;
    return return_row;
end;
/

create or replace function read_reimbursement
    (in_reimbursementid number) return reimbursement%rowtype
as
    return_row reimbursement%rowtype;
begin
    select * into return_row from reimbursement where reimbursementid=in_reimbursementid;
    return return_row;
end;
/

create or replace procedure update_employee
    (in_employeeid number, in_managerid number, in_username varchar2, in_password varchar2)
as
begin
    update employee 
        set managerid=in_managerid, username=in_username, password=in_password
        where employeeid=in_employeeid;
    commit;
end;
/

create or replace procedure update_information
    (in_informationid number, in_employeeid number, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
begin
    update information
        set employeeid=in_employeeid, firstname=in_firstname, middlename=in_middlename, lastname=in_lastname
        where informationid=in_informationid;
    commit;
end;
/

create or replace procedure update_reimbursement
    (in_reimbursementid number, in_employeeid number, in_status varchar2, in_image varchar2, in_category varchar2)
as
begin
    update reimbursement
        set employeeid=in_employeeid, status=in_status, image=in_image, category=in_category
        where reimbursementid=in_reimbursementid;
    commit;
end;
/

create or replace procedure delete_employee
    (in_employeeid number)
as
begin
    delete from employee where employeeid=in_employeeid;
    commit;
end;
/

create or replace procedure delete_information
    (in_informationid number)
as
begin
    delete from information where informationid=in_informationid;
    commit;
end;
/

create or replace procedure delete_manager
    (in_managerid number)
as
begin
    delete from manager where managerid=in_managerid;
    commit;
end;
/

create or replace procedure delete_reimbursement
    (in_reimbursementid number)
as
begin
    delete from reimbursement where reimbursementid=in_reimbursementid;
    commit;
end;
/