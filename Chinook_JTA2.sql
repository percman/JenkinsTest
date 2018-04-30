--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee WHERE firstname = 'Andrew' AND REPORTSTO IS NULL;

--Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album ORDER BY title DESC; --DESC specifies that the result set should be sorted descendingly

--Update Aaron Mitchell in Customer table to Robert Walter
SELECT * FROM customer WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
UPDATE customer SET firstname='Robert', lastname = 'Walter' WHERE customerid = 32;

--Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN to_Date('06-01-2003', 'mm-dd-yyyy') AND to_Date('03-01-2004', 'mm-dd-yyyy');

--Delete a record in Customer table where the name is Robert Walker (Use subquery)
SELECT * FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter';
DELETE FROM customer WHERE (SELECT * FROM customer where firstname = 'Robert' AND lastname = 'Walter');
--Create an inner join that joins customers and orders and specifies the name of customer and the invoiceID
SELECT * FROM invoice;
SELECT * FROM customer;
SELECT customer.firstname, customer.lastname, invoice.invoiceid 
    FROM customer 
    INNER JOIN invoice 
    ON customer.customerid = invoice.customerid;

--Teacher's answer
SELECT CONCAT(CONCAT(c.firstname, ' '), c.lastname) as Name, i.invoiceid
    FROM customer c
    INNER JOIN invoice i
    ON c.customerid = i.customerid;
    
--Alternate Easier version with concat operator
SELECT c.firstname || ' ' || c.lastname as Customer_Name, i.invoiceid
    From customer c
    INNER JOIN invoice i
    ON c.customerid = i.customerid;
    
    
    
    
-- super quick unioni
SELECT * FROM employee
UNION
SELECT * FROM invoice;




--Create an outer join that joins the customer and invoice table, specifying the CustomerID, firstname, lastname, invoiceID, and total
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total 
FROM customer 
FULL OUTER JOIN invoice 
ON customer.customerid = invoice.customerid;
--Create a right join that joins album and artist specifying artist name and title
SELECT artist.name, album.title
FROM artist
RIGHT JOIN album
ON artist.artistid = album.artistid;
--Create a cross join that joins album and artist and sorts by artist name in ascending order
SELECT *
FROM album
CROSS JOIN artist
ON artist.artistid = album.artistid ORDER BY ASC

--Perform a self-join on the employee table, joining on the reports to column
