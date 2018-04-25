--    1. Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. 
SELECT * FROM employee WHERE FirstName = 'Andrew' AND REPORTSTO IS NULL;

--   2. Select all albums in Album table and sort result set in descending order by title. 
SELECT * FROM album ORDER BY title DESC;

--3. Update Aaron Mitchell in Customer table to Robert Walter 
SELECT * FROM customer where customerid = '32';
UPDATE customer SET firstName = 'Robert' , lastName = 'Walter' WHERE customerid = '32';
-- 4. Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN to_date('06-01-2003', 'mm-dd-yyyy') and to_date('03-01-2004', 'mm-dd-yyyy');

--  5. Delete a record in Customer table where the name is Robert Walter 
-- (There may be constraints that rely on this, find out how to resolve them). 
DELETE customer WHERE firstName = 'Robert' AND lastName = 'Walter';

SELECT * FROM JOIN customer, employee WHERE firstName = 'Robert' AND lastName = 'Walter';

SELECT * FROM invoice;
SELECT * FROM OUTER JOIN customer ON customer.customerid = invoice.customerid;

--   6. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. 
SELECT * FROM customer;


SELECT c.firstname || ' ' || c.lastname as Customer_Name, i.invoiceid
    FROM customer c
    INNER JOIN invoice i
    ON c.customerid = c.invoiceid;