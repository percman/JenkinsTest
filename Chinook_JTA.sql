--  1. Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. 
SELECT *
FROM employee
WHERE firstname = 'Andrew'
AND reportsto IS NULL; -- IS NULL, not = null

--  2. Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM album
ORDER BY title DESC; --DESC means descending order. Can use ASC for ascending order, but that's the default

--  3. Update Aaron Mitchell in Customer table to Robert Walter 
SELECT *
FROM customer
WHERE firstname = 'Aaron'
AND lastname = 'Mitchell'; -- Take id, use in next statement

UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE customerid = 32;

--  4. Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM employee
WHERE hiredate BETWEEN to_date('06-01-2003', 'mm-dd-yyyy') AND to_date('03-01-2004', 'mm-dd-yyyy');

--  5. Delete a record in Customer table where the name is Robert Walter 
-- (There may be constraints that rely on this, find out how to resolve them). 
DELETE FROM invoiceline
WHERE invoicelineid IN(
    SELECT invoicelineid
    FROM invoiceline
    INNER JOIN invoice ON invoiceline.invoiceid = invoice.invoiceid
    INNER JOIN customer ON invoice.customerid = customer.customerid
    WHERE firstname = 'Robert' AND lastname = 'Walter');

DELETE FROM invoice
WHERE customerid IN(
SELECT customerid FROM
customer NATURAL JOIN invoice
WHERE firstname = 'Robert' AND lastname = 'Walter');

DELETE FROM customer
WHERE firstname = 'Robert'
AND lastname = 'Walter';

-- 6. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. 

SELECT customer.firstname, customer.lastname, invoice.invoiceid
FROM customer
INNER JOIN invoice ON customer.customerid = invoice.customerid;

-- 7. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total. 

SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
FULL OUTER JOIN invoice ON customer.customerid = invoice.customerid;

-- 8. Create a right join that joins album and artist specifying artist name and title.

SELECT artist.name, album.title
FROM album
RIGHT JOIN artist ON album.artistid = artist.artistid;

-- 9. Create a cross join that joins album and artist and sorts by artist name in ascending order.

SELECT *
FROM album
CROSS JOIN artist
ORDER BY artist.name;

-- 10. Perform a self-join on the employee table, joining on the reportsto column. 

SELECT *
FROM employee A, employee B
WHERE A.reportsto = B.employeeid;

--Write a SQL Query that contains the names of all tracks that are longer than 6 minutes
--    

SELECT * FROM track
WHERE 6 * 60 * 1000 < milliseconds;

--Write a SQL Query to find the biggest song (which takes up the most space)
--    

SELECT *
FROM track
WHERE bytes = (SELECT MAX(bytes) FROM track);

--Write a SQL Query that contains the titles of all albums with tracks longer than 6 minutes in them 
--    

SELECT DISTINCT title
FROM album
NATURAL JOIN track
WHERE 6 * 60 * 1000 < milliseconds;

--Write a SQL Query that contains the albumId and number of songs in the album 
--    

SELECT albumid, COUNT(*)
FROM track
GROUP BY albumid;

--Write a SQL query that contains artist's names and the number of tracks they have produced (assume an artist produced a track if it appears in one of their albums)
--    

SELECT artist.name, COUNT (*)
FROM artist
INNER JOIN album ON artist.artistid = album.artistid
INNER JOIN track ON album.albumid = track.albumid
GROUP BY artist.name;

--Write a SQL Query that returns the most purchased media type
--

SELECT mediatype.name
FROM mediatype
INNER JOIN track ON mediatype.mediatypeid = track.mediatypeid
INNER JOIN invoiceline ON track.trackid = invoiceline.trackid
GROUP BY mediatype.name
ORDER BY COUNT(invoiceline.trackid) DESC
FETCH FIRST ROW ONLY;

--Write a SQL Query showing customers not in the US

SELECT * FROM customer
WHERE country <> 'USA';

--Write a SQL Query showing a unique list of billing countries on the Invoice table

SELECT DISTINCT billingcountry
FROM invoice;

--Write a SQL Query that shows the Invoice Total, Customer Name, Country, and Sales agent for all invoices and customers 

SELECT invoice.total, customer.firstname, customer.lastname, customer.country, employee.firstname, employee.lastname
FROM invoice
FULL OUTER JOIN customer ON invoice.customerid = customer.customerid
LEFT JOIN employee ON customer.supportrepid = employee.employeeid;

--Write a SQL Query that shows all Tracks, but displays no IDs. Should also include the Album name, Media Type, and Genre

SELECT track.name, album.title, mediatype.name, genre.name
FROM track
LEFT JOIN album ON track.albumid = album.albumid
LEFT JOIN mediatype ON track.mediatypeid = mediatype.mediatypeid
LEFT JOIN genre ON track.genreid = genre.genreid;

--Write a SQL Query that returns the Top 40 Songs for 2013

SELECT track.name
FROM track
INNER JOIN invoiceline ON track.trackid = invoiceline.trackid
INNER JOIN invoice ON invoiceline.invoiceid = invoice.invoiceid
WHERE EXTRACT(year FROM invoice.invoicedate) = 2013
GROUP BY track.name
ORDER BY COUNT(invoiceline.trackid) DESC
FETCH FIRST 40 ROWS ONLY;

--Write a SQL Query that shows which sales agent made the most in sales overall

SELECT employee.firstname, employee.lastname
FROM employee
INNER JOIN customer ON customer.supportrepid = employee.employeeid
INNER JOIN invoice ON customer.customerid = invoice.customerid
GROUP BY employee.lastname, employee.firstname
ORDER BY SUM(invoice.total) DESC
FETCH FIRST ROW ONLY;

--Write a SQL Query that shows the top 3 best selling artists 

SELECT artist.name
FROM artist
INNER JOIN album ON artist.artistid = album.artistid
INNER JOIN track ON track.albumid = album.albumid
INNER JOIN invoiceline ON invoiceline.trackid = track.trackid
GROUP BY artist.name
ORDER BY COUNT(invoiceline.trackid) DESC
FETCH FIRST 3 ROWS ONLY;

SELECT * FROM track;

--Write a SQL Query that returns which albums have no Heavy Metal tracks

SELECT title
FROM album
WHERE NOT EXISTS (
    SELECT *
    FROM track
    INNER JOIN genre ON track.genreid = genre.genreid
    WHERE album.albumid = track.albumid AND genre.name = 'Heavy Metal');

--Write a SQL Query to find the the managers of employees supporting Brazilian customers 

SELECT DISTINCT A.firstname, A.lastname
FROM employee A, employee B
INNER JOIN customer ON B.employeeid = customer.supportrepid
WHERE B.reportsTo = A.employeeid
AND customer.country = 'Brazil';