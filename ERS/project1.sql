drop table employee cascade constraints purge;
drop table information cascade constraints purge;
drop table reimbursement cascade constraints purge;
drop sequence pk_employee;
drop sequence pk_reimbursement;

create table employee(
    employeeid number,
    username varchar2(100) not null,
    password varchar2(100) not null,
    constraint pk_employee_employeeid primary key(employeeid),
    constraint u_username unique(username)
);

create table information(
    employeeid number,
    firstname varchar2(100) not null,
    middlename varchar2(100),
    lastname varchar2(100) not null,
    constraint pk_information_employeeid primary key(employeeid),
    constraint fk_employee foreign key (employeeid) references employee(employeeid)
);

create table reimbursement(
    reimbursementid number,
    requestorid number not null,
    approverid number,
    category varchar2(100),
    status varchar2(100),
    image varchar2(100),
    constraint pk_reimbursementid primary key(reimbursementid),
    constraint fk_requestorid foreign key(requestorid) references employee(employeeid),
    constraint fk_approverid foreign key(approverid) references employee(employeeid),
    constraint ck_approver check(requestorid <> approverid)
);

create sequence pk_employee
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create sequence pk_reimbursement
    minvalue 0
    maxvalue 999
    start with 0
    increment by 1
    nocache;

create or replace function hash
    (in_user varchar2, in_password varchar2) return varchar2
is
extra varchar2(10) := 'pepper';
begin
    return to_char(DBMS_OBFUSCATION_TOOLKIT.MD5(
        input => UTL_I18N.STRING_TO_RAW(data => in_user || in_password || extra)));
end;
/

create or replace trigger before_create_employee
    before insert on employee
    for each row
begin
    if :new.employeeid is null then
        select pk_employee.nextval into :new.employeeid from dual;
    end if;
    select hash(:new.username, :new.password) into :new.password from dual;
end;
/

create or replace trigger before_update_employee
    before update on employee
    for each row
begin
    select hash(:new.username, :new.password) into :new.password from dual;
end;
/

create or replace trigger before_create_reimbursement
    before insert on reimbursement
    for each row
begin
    if :new.reimbursementid is null then
        select pk_reimbursement.nextval into :new.reimbursementid from dual;
    end if;
end;
/

create or replace procedure create_employee
    (in_username varchar2, in_password varchar2)
as
begin
    insert into employee (employeeid, username, password)
        values (null, in_username, in_password);
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

create or replace procedure update_employee
    (in_employeeid number, in_username varchar2, in_password varchar2)
as
    password_hash varchar2(100);
begin
    update employee 
        set username=in_username, password=in_password
        where employeeid=in_employeeid;
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

create or replace procedure create_information
    (in_employeeid number, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
begin
    insert into information (employeeid, firstname, middlename, lastname)
        values (in_employeeid, in_firstname, in_middlename, in_lastname);
    commit;
end;
/

create or replace function read_information
    (in_employeeid number) return information%rowtype
is
    return_row information%rowtype;
begin
    select * into return_row from information where employeeid=in_employeeid;
    return return_row;
end;
/

create or replace procedure update_information
    (in_employeeid number, in_firstname varchar2, in_middlename varchar2, in_lastname varchar2)
as
begin
    update information set firstname=in_firstname, middlename=in_middlename, lastname=in_lastname
        where employeeid=in_employeeid;
    commit;
end;
/

create or replace procedure delete_information
    (in_employeeid number)
as
begin
    delete from information where employeeid=in_employeeid;
    commit;
end;
/

create or replace procedure create_reimbursement
    (in_requestorid number, in_approverid number, in_category varchar2, in_status varchar2, in_image varchar2)
as
begin
    insert into reimbursement (reimbursementid, requestorid, approverid, category, status, image)
        values(null, in_requestorid, in_approverid, in_category, in_status, in_image);
    commit;
end;
/

create or replace function read_reimbursement 
    (in_reimbursementid number) return reimbursement%rowtype
is
    return_row reimbursement%rowtype;
begin
    select * into return_row from reimbursement where reimbursementid=reimbursementid;
    return return_row;
    commit;
end;
/

create or replace procedure update_reimbursement
    (in_reimbursementid number, in_requestorid number, in_approverid number, in_category number, 
    in_status varchar2, in_image varchar2)
as
begin
    update reimbursement set reimbursement=in_reimbursementid, requestorid=in_requestorid, 
        approverid=in_approverid, category=in_category, status=in_status, image=in_image;
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


create_employee('curtishilgenberg', 'password');
create_employee('cameronskaggs', 'password');
create_employee('danielabramson', 'password');
create_information(0, 'curtis', null, 'hilgenberg');
create_information(1, 'cameron', null, 'skaggs');
create_information(2, 'daniel', null, 'abramson');
create_reimbursement(0, null, 'lodging', 'pending', 'http://imgur.com');
create_reimbursement(1, null, 'food', 'approved', 'http://imgur.com');
create_reimbursement(2, null, 'transportation', 'rejected', 'http://imgur.com');

select * from employee;
select * from information;
select * from reimbursement;
    