SELECT * FROM employee;

-- Slect all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee
    WHERE firstname = 'Andrew'
    AND reportsto IS NULL;

    
-- Select all albums in Album table and sort result set in decending order by title.
SELECT * FROM album 
    ORDER BY title DESC;
    -- DESC specifies that the result set should be descendingly

    
-- Update Aaron Mitchell in Customer table to Robert Walter
SELECT * FROM customer
    WHERE firstname = 'Aaron'
    AND lastname = 'Mitchell';
UPDATE customer SET firstname = 'Robert', lastname = 'Walter'
    WHERE customerid = 32;
SELECT * FROM customer
    WHERE customerid = 32;
    
    
-- Select all employees hired between 1st of June 2003 and 1st March 2004
SELECT * FROM employee
    WHERE hiredate BETWEEN to_date('06-01-2003', 'mm-dd-yyyy') AND to_date('03-01-2004', 'mm-dd-yyyy');


-- Select Delete a record in Customer table where the name is Robert Walter 
-- (There may be constraints that rely on this, find out how to resolve them).
SELECT * FROM customer;                         -- checking the whole customer table
SELECT * FROM invoice WHERE customerid = 32;    -- checking the invoice table for all of Robert's invoices
SELECT * FROM invoiceline;                      -- checking the whole invoiceline table
SELECT * FROM invoiceline
    JOIN invoice ON invoice.invoiceid = invoiceline.invoiceid 
    WHERE invoice.customerid = 32;              -- checking for only Robert's entries in the invoiceline table
DELETE FROM invoiceline WHERE invoiceline.invoiceid IN
    (SELECT invoiceline.invoiceid FROM invoiceline
        JOIN invoice ON invoice.invoiceid = invoiceline.invoiceid 
        WHERE invoice.customerid = 32);         -- deleting all of Robert's entries from the invoiceline table
DELETE FROM invoice WHERE customerid = 32;      -- deleting all of Robert's entries from the invoice table
DELETE FROM customer WHERE customerid = 32;     -- deleting Robert from the customer table
SELECT * FROM customer;                         -- making sure Robert is no longer on the customer table

 -- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. 
SELECT * FROM customer;
SELECT * FROM invoice;

SELECT firstname, lastname, invoiceid FROM invoice
    INNER JOIN customer ON customer.customerid = invoice.customerid;

-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total. 
SELECT customerid, firstname, lastname, invoiceid, total FROM invoice
    OUTER JOIN customer ON customer.customerid = invoice.customerid;
    
-- Create a right join that joins album and artist specifying artist name and title.


-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
    
    
-- Perform a self-join on the employee table, joining on the reportsto column. 

