-- Write a SQL Query that contains the names of all tracks that are longer than 6 minutes
SELECT name FROM track
    WHERE milliseconds > 600000;

-- Write a SQL Query to find the biggest song (which takes up the most space)
SELECT * FROM track
    WHERE bytes = (SELECT MAX(bytes) FROM track);
    
-- Write a SQL Query that contains the titles of all albums with tracks longer than 6 minutes in them 
SELECT title FROM album
    INNER JOIN track ON track.albumid = album.albumid
    WHERE track.milliseconds > 600000;
    
-- Write a SQL Query that contains the albumId and number of songs in the album 
SELECT * FROM track;
SELECT * FROM album;
SELECT albumid, COUNT(*) AS tracksonalbum FROM track
    GROUP BY albumid;
    
    
-- Write a SQL query that contains artist's names and the number of tracks they have produced 
-- (assume an artist produced a track if it appears in one of their albums)
SELECT * FROM track;
SELECT * FROM album;
SELECT * FROM artist;
-- SELECT artist.name, COUNT(*) AS tracksproduced FROM track;
    
-- Write a SQL Query that returns the most purchased media type
-- SELECT * FROM mediatype
--    WHERE mediatype.mediatypeid = ;

    
-- Write a SQL Query showing customers not in the US
SELECT * FROM customer
    WHERE NOT country = 'USA';

-- Write a SQL Query showing a unique list of billing countries on the Invoice table
SELECT DISTINCT billingcountry FROM invoice;

-- Write a SQL Query that shows the Invoice Total, Customer Name, Country, and Sales agent for all invoices and customers 
SELECT * FROM customer;
SELECT * FROM invoice;
SELECT * FROM employee;
-- SELECT invoice.total, customer.firstname, customer.lastname, customer.country, employee.firstname, employee.lastname FROM customer
--    WHERE customer.invoiceid = invoice.invoiceid
--    AND customer.supportrepid = employee.employeeid;

-- Write a SQL Query that shows all Tracks, but displays no IDs. Should also include the Album name, Media Type, and Genre
SELECT * FROM track;

-- Write a SQL Query that returns the Top 40 Songs for 2013
SELECT * FROM track;


-- Write a SQL Query that shows which sales agent made the most in sales overall


-- Write a SQL Query that shows the top 3 best selling artists 


-- Write a SQL Query that returns which albums have no Heavy Metal tracks


-- Write a SQL Query to find the the managers of employees supporting Brazilian customers 


