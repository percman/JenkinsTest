drop table employee cascade constraints purge;
drop table manager cascade constraints purge;
drop table information cascade constraints purge;
drop table reimbursement cascade constraints purge;
drop sequence pks_employee;
drop sequence pks_information;
drop sequence pks_manager;
drop sequence pks_reimbursement;

create table employee(
    employeeid number not null,
    managerid number,
    username varchar2(100) not null unique,
    password varchar2(100) not null,
    constraint pk_employeeid primary key(employeeid)
);

create table manager(
    managerid number not null,
    employeeid number not null,
    constraint pk_managerid primary key(managerid),
    constraint fk__manager_employeeid foreign key(employeeid) references employee
);

--alter table employee
--    add constraint fk_employee_managerid foreign key(managerid) references manager;

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
    managerid number not null,
    status varchar2(100) not null,
    image varchar2(100),
    category varchar2(100),
    constraint pk_reimbursementid primary key(reimbursementid),
    constraint fk_reimbursement_employeeid foreign key(employeeid) references employee(employeeid),
    constraint fk_reimbursement_managerid foreign key(managerid) references manager(managerid)
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

create or replace trigger before_update_employee
before update
on employee
for each row
begin
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
    (in_username varchar2, in_password varchar2)
as
begin
    insert into employee(employeeid, managerid, username, password)
        values(null, null, in_username, in_password);
    commit;
end;
/

create or replace procedure create_information
    (in_username varchar2, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
    in_employeeid number;
    in_informationid number;
begin
    select employeeid into in_employeeid from employee where username=in_username;
    insert into information(informationid, employeeid, firstname, middlename, lastname)
        values(in_informationid, in_employeeid, in_firstname, in_middlename, in_lastname);
    commit;
end;
/

create or replace procedure create_manager
    (in_username varchar2)
as
    in_employeeid number;
begin
    select employeeid into in_employeeid from employee where username=in_username;
    insert into manager(managerid, employeeid)
        values(null, in_employeeid);
    update employee set managerid=
        (select m.managerid from manager m, employee e where m.employeeid=e.employeeid and e.username=in_username);
    commit;
end;
/

create or replace procedure create_reimbursement
    (in_username varchar2, in_status varchar2, in_image varchar2, in_category varchar2)
as
    in_managerid number;
    in_employeeid number;
begin
    select employeeid into in_employeeid from employee where username=in_username;
    select managerid into in_managerid from manager where employeeid=in_employeeid;
    insert into reimbursement(reimbursementid, employeeid, managerid, status, image, category)
        values(null, in_employeeid, in_managerid, in_status, in_image, in_category);
    commit;
end;
/

create or replace function read_employee
    (in_username varchar2) return employee%rowtype
as
    in_employeeid number;
    return_row employee%rowtype;
begin
    select employeeid into in_employeeid from employee where username=in_username;
    select * into return_row from employee where employeeid=in_employeeid;
    return return_row;
end;
/

create or replace function read_information
    (in_username varchar2) return information%rowtype
as
    in_informationid number;
    return_row information%rowtype;
begin
    select i.informationid into in_informationid from information i, employee e
        where i.employeeid=e.employeeid and e.username=in_username;
    select * into return_row from information where informationid=in_informationid;
    return return_row;
end;
/

create or replace function read_manager
    (in_username varchar2) return manager%rowtype
as
    return_row manager%rowtype;
begin
    select * into return_row from manager where employeeid=
        (select employeeid from employee where username=in_username);
    return return_row;
end;
/

create or replace function read_reimbursement
    (in_username varchar2) return reimbursement%rowtype
as
    return_row reimbursement%rowtype;
begin
    select * into return_row from reimbursement where reimbursementid=
        (select r.reimbursementid from reimbursement r, employee e
            where r.employeeid=e.employeeid and e.username=in_username);
    return return_row;
end;
/

create or replace procedure update_employee
    (in_managerusername varchar2, in_username varchar2, in_password varchar2)
as
    in_managerid number;
begin
    select m.employeeid into in_managerid from manager m, employee e 
        where m.employeeid=e.employeeid and e.username=in_managerusername;
    update employee 
        set managerid=in_managerid, username=in_username, password=in_password
        where username=in_username;
    commit;
end;
/

create or replace procedure update_information
    (in_username varchar2, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
    in_informationid number;
begin
    select informationid into in_informationid from information i, employee e
        where i.employeeid=e.employeeid and username=in_username;
    update information
        set firstname=in_firstname, middlename=in_middlename, lastname=in_lastname
        where informationid=in_informationid;
    commit;
end;
/

create or replace procedure update_reimbursement
    (in_username varchar2, in_status varchar2, in_image varchar2, in_category varchar2)
as
    in_reimbursementid number;
begin
    select reimbursementid into in_reimbursementid from reimbursement r, employee e
        where r.employeeid=e.employeeid and e.username=in_username;
    update reimbursement
        set status=in_status, image=in_image, category=in_category
        where reimbursementid=in_reimbursementid;
    commit;
end;
/

create or replace procedure delete_employee
    (in_username varchar2)
as
begin
    delete from employee where username=in_username;
    commit;
end;
/

create or replace procedure delete_information
    (in_username varchar2)
as
begin
    delete from information where employeeid=
        (select employeeid from employee where username=in_username);
    commit;
end;
/

create or replace procedure delete_manager
    (in_username varchar2)
as
    in_employeeid number;
begin
    select m.employeeid into in_employeeid from employee e, manager m 
        where e.employeeid=m.employeeid and e.username=in_username; 
    delete from manager where employeeid=in_employeeid;
    commit;
end;
/

create or replace procedure delete_reimbursement
    (in_username varchar2)
as
begin
    delete from reimbursement where employeeid=
        (select employeeid from employee where username=in_username);
    commit;
end;
/

begin
create_employee('user', 'password');
create_manager('user');
create_information('user', 'first', 'middle', 'last');
create_reimbursement('user', 'status', 'category', 'image');
create_reimbursement('user', 'status', 'category', 'image');
end;
/


select * from employee;
select * from manager;
select * from information;
select * from reimbursement;

