-- **************************************** PRACTICE SET 1 ****************************************

--1. Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. 
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2. Select all albums in Album table and sort result set in descending order by title. 
SELECT * FROM album ORDER BY title DESC;

--3. Update Aaron Mitchell in Customer table to Robert Walter 
UPDATE customer SET firstname = 'Robert', lastname = 'Walter' WHERE customerid = 32;

--4. Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * 
FROM employee 
WHERE hiredate BETWEEN to_date('06-01-2003', 'mm-dd-yyyy')
    AND to_date('03-01-2004', 'mm-dd-yyyy');
--5. Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them). 

--6. Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId. 


--7. Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total. 

--8. Create a right join that joins album and artist specifying artist name and title.

--9. Create a cross join that joins album and artist and sorts by artist name in ascending order.

--10. Perform a self-join on the employee table, joining on the reportsto column. 



    
    
    
    

-- **************************************** PRACTICE SET 2 ****************************************
--Write a SQL Query that contains the names of all tracks that are longer than 6 minutes
SELECT albumid FROM track WHERE milliseconds > 360000;

--Write a SQL Query to find the biggest song (which takes up the most space)
SELECT * FROM track WHERE milliseconds = (SELECT MAX(milliseconds) FROM track);
    
--Write a SQL Query that contains the titles of all albums with tracks longer than 6 minutes in them 
SELECT title FROM album INNER JOIN track ON track.albumid = album.albumid WHERE milliseconds > 360000;

--Write a SQL Query that contains the albumId and number of songs in the album 
SELECT album.ALBUMID,COUNT(track.albumid) 
FROM track,album 
WHERE album.albumid = track.ALBUMID 
GROUP BY album.albumid 
ORDER BY album.albumid;

--Write a SQL query that contains artist's names and the number of tracks they have produced 
--(assume an artist produced a track if it appears in one of their albums)
SELECT track.composer, COUNT(track.albumid)
FROM track 
WHERE albumid IS NOT NULL
GROUP BY composer
ORDER BY composer;
    
--Write a SQL Query that returns the most purchased media type
SELECT * 
FROM 
    (SELECT COUNT(t2.mediatypeid), t1.mediatypeid
    FROM track t1, track t2 
    GROUP BY t1.mediatypeid
    ORDER BY t1.mediatypeid) 
WHERE ROWNUM <= 1;

--Write a SQL Query showing customers not in the US
SELECT * 
FROM customer 
WHERE country <> 'USA' 
ORDER BY country;

--Write a SQL Query showing a unique list of billing countries on the Invoice table
SELECT UNIQUE(billingcountry )
FROM invoice
ORDER BY billingcountry;

--Write a SQL Query that shows the Invoice Total, Customer Name, Country, and Sales agent for all invoices and customers 
SELECT i.total, c.firstname, c.lastname
FROM invoice i, customer c;

--Write a SQL Query that shows all Tracks, but displays no IDs. Should also include the Album name, Media Type, and Genre
SELECT t1.name, t1.composer,t1.milliseconds,t1.bytes, a1.title, m1.name, g1.name
FROM track t1
JOIN album a1
ON t1.albumid = a1.ALBUMID
JOIN mediatype m1
ON t1.mediatypeid = m1.mediatypeid
JOIN genre g1
ON t1.genreid = g1.genreid
ORDER BY composer;


--Write a SQL Query that returns the Top 40 Songs for 2013
--assuming top meaning tracks sold, not gross sales 
-- this information is incorrect; sometimes the composer and trackID is different for the same song! ugh! 
SELECT *
FROM(
    SELECT track.name, COUNT(invoiceline.trackid) as sales
    FROM track
    LEFT JOIN invoiceline
    ON track.trackid = invoiceline.trackid
    GROUP BY track.name
    ORDER BY sales DESC)
WHERE ROWNUM <= 40;

--Write a SQL Query that shows which sales agent made the most in sales overall
-- it's not pretty, and the data querried wasn't specificed, so I gave them all employee information 
SELECT *
FROM(
    SELECT e1.employeeid, COUNT(customer.supportrepid) as sales
    FROM employee e1
    LEFT JOIN customer
    ON e1.employeeid = customer.supportrepid
    GROUP BY e1.employeeid
    ORDER BY sales DESC) e3
JOIN employee e2
ON e3.employeeid = e2.employeeid
WHERE ROWNUM <= 1;

--Write a SQL Query that shows the top 3 best selling artists 
-- incorrect, as sometimes the composer is repeated. ugh. closer though! 
SELECT t4.composer, t4.composersales
FROM (
    SELECT t2.composer, COUNT(t3.name)+t3.sales as composersales
    FROM(
        SELECT t1.name, COUNT(v1.trackid) as sales
        FROM track t1
        LEFT JOIN invoiceline v1
        ON t1.trackid = v1.trackid
        GROUP BY t1.name
        ORDER BY sales DESC) t3
    JOIN track t2
    ON t2.name = t3.name
    WHERE t2.composer IS NOT NULL
    GROUP BY t2.composer, t3.sales
    ORDER BY composersales DESC) t4
WHERE ROWNUM <= 4;
    
--Write a SQL Query that returns which albums have no Heavy Metal tracks


--Write a SQL Query to find the the managers of employees supporting Brazilian customers 



