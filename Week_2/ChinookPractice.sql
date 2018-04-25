--Chinook Practice
--Andrew Ahn
--andrewsrahn@gmail.com
--Revature
--JTA 1804
--April 25, 2018

--	1. Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. 
select * from employee where firstname='Andrew' and reportsto is null;

--	2. Select all albums in Album table and sort result set in descending order by title. 
select * from album order by title desc;

--	3. Update Aaron Mitchell in Customer table to Robert Walter 
update customer set firstname='Robert', lastname='Walter' where customerid=
    (select customerid from customer where firstname='Aaron' and lastname='Mitchell');
    
--	4. Select all employees hired between 1st of June 2003 and 1st of March 2004
select * from employee where hiredate between to_date('06-01-2003', 'mm-dd-yyyy') and to_date('03-01-2004', 'mm-dd-yyyy');
--	5. Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out 
--  how to resolve them). 

alter table invoice drop constraint FK_INVOICECUSTOMERID;

delete from customer where customerid=
    (select customerid from customer where firstname='Robert' and lastname='Walter');

alter table invoice add constraint FK_INVOICECUSTOMERID foreign key (customerid) references customer(customerid);
--Error report -
--ORA-02298: cannot validate (CHINOOK.FK_INVOICECUSTOMERID) - parent keys not found
--02298. 00000 - "cannot validate (%s.%s) - parent keys not found"
--*Cause:    an alter table validating constraint failed because the table has
--           child records.
--*Action:   Obvious
--ALTER TABLE your_table
--add CONSTRAINT ACTIVEPROG_FKEY1 FOREIGN KEY(ActiveProgCode) REFERENCES PROGRAM(ActiveProgCode)
--    ON DELETE CASCADE;

--	6. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. 
select c.firstname, c.lastname, i.invoiceid from customer c inner join invoice i on c.customerid=i.customerid;

--	7. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, 
--  and total. 
select c.customerid, c.firstname, c.lastname, i.invoiceid, i.total from customer c full outer join invoice i on 
    c.customerid = i.customerid;

--	8. Create a right join that joins album and artist specifying artist name and title.
select art.name, alb.title from album alb right join artist art on alb.artistid=art.artistid;

--	9. Create a cross join that joins album and artist and sorts by artist name in ascending order.
select * from album alb cross join artist art where alb.artistid=art.artistid order by art.name asc;

--	10. Perform a self-join on the employee table, joining on the reportsto column. 
select * from employee e1, employee e2 where e1.reportsto=e2.reportsto;