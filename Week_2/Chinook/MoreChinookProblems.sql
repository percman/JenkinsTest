-- Write a SQL Query that contains the names of all tracks that are longer than 6 minutes
-- 
-- COMPLETE
SELECT name FROM track
    WHERE milliseconds > 600000;

-- Write a SQL Query to find the biggest song (which takes up the most space)
-- 
-- COMPLETE
SELECT * FROM track
    WHERE bytes = (SELECT MAX(bytes) FROM track);
    
-- Write a SQL Query that contains the titles of all albums with tracks longer than 6 minutes in them 
-- 
-- COMPLETE
SELECT title FROM album
    INNER JOIN track ON track.albumid = album.albumid
    WHERE track.milliseconds > 600000;
    
-- Write a SQL Query that contains the albumId and number of songs in the album 
-- 
-- COMPLETE
SELECT * FROM track;
SELECT * FROM album;
SELECT albumid, COUNT(*) AS tracksonalbum FROM track
    GROUP BY albumid;
    
    
-- Write a SQL query that contains artist's names and the number of tracks they have produced 
-- (assume an artist produced a track if it appears in one of their albums)
-- 
-- COMPLETE
SELECT * FROM track;
SELECT * FROM album;
SELECT * FROM artist;
SELECT artist.name, COUNT(*) AS tracksproduced FROM track
    INNER JOIN album ON album.albumid = track.albumid
    INNER JOIN artist ON artist.artistid = album.artistid
    GROUP BY artist.name;
    
-- Write a SQL Query that returns the most purchased media type
-- 
-- COMPLETE
SELECT * FROM invoiceline;
SELECT * FROM track;
SELECT * FROM mediatype;

SELECT * FROM
    (SELECT mediatype.name, COUNT(*) AS copiesbought FROM invoiceline
        INNER JOIN track ON track.trackid = invoiceline.trackid
        INNER JOIN mediatype ON mediatype.mediatypeid = track.mediatypeid
        GROUP BY mediatype.name
        ORDER BY copiesbought DESC)
    WHERE ROWNUM <=1;
    

    
-- Write a SQL Query showing customers not in the US
-- 
-- COMPLETE
SELECT * FROM customer
    WHERE NOT country = 'USA';

-- Write a SQL Query showing a unique list of billing countries on the Invoice table
-- 
-- COMPLETE
SELECT DISTINCT billingcountry FROM invoice;

-- Write a SQL Query that shows the Invoice Total, Customer Name, Country, and Sales agent for all invoices and customers 
-- 
-- COMPLETE
SELECT * FROM customer;
SELECT * FROM invoice;
SELECT * FROM employee;
SELECT invoice.total, customer.firstname, customer.lastname, customer.country, employee.firstname, employee.lastname FROM customer
    INNER JOIN invoice ON invoice.customerid = customer.customerid
    INNER JOIN employee ON employee.employeeid = customer.supportrepid;

-- Write a SQL Query that shows all Tracks, but displays no IDs. Should also include the Album name, Media Type, and Genre
-- 
-- COMPLETE
SELECT * FROM track;
SELECT * FROM album;
SELECT * FROM genre;
SELECT * FROM mediatype;
SELECT track.name, album.title, mediatype.name, genre.name FROM track
    INNER JOIN album ON album.albumid = track.albumid
    INNER JOIN mediatype ON mediatype.mediatypeid = track.mediatypeid
    INNER JOIN genre ON genre.genreid = track.genreid;

-- Write a SQL Query that returns the Top 40 Songs for 2013
SELECT * FROM track;


-- Write a SQL Query that shows which sales agent made the most in sales overall
SELECT * FROM employee;
SELECT * FROM customer;
--SELECT e.* FROM employee e
--    WHERE e.employeeid IN (
--        SELECT 
--
--;



-- Write a SQL Query that shows the top 3 best selling artists 


-- Write a SQL Query that returns which albums have no Heavy Metal tracks


-- Write a SQL Query to find the the managers of employees supporting Brazilian customers 


