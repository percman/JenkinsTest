/*Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. */
SELECT * 
FROM employee 
WHERE firstname = 'Andrew' AND reportsto IS NULL;
/*Select all albums in Album table and sort result set in descending order by title. */
SELECT * 
FROM album 
ORDER BY title DESC;
/*Update Aaron Mitchell in Customer table to Robert Walter  */
SELECT * 
FROM customer
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
/*Select all employees hired between 1st of June 2003 and 1st of March 2004*/
SELECT * 
FROM employee 
WHERE hiredate 
BETWEEN TO_DATE('06-01-2003','mm-dd-yyyy') AND TO_DATE('03-01-2004','mm-dd-yyyy');
/*Delete a record in Customer table where the name is Robert Walter */

DELETE 
FROM INVOICELINE 
WHERE INVOICEID = 342;

DELETE
FROM INVOICE 
WHERE CUSTOMERID = 32;

DELETE 
FROM customer 
WHERE firstname = 'robert' AND lastname = 'walter'; 
/*Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId */
SELECT firstname,lastname,invoiceid 
FROM customer 
INNER JOIN invoice
ON customer.customerid = invoice.customerid;
/*Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total*/
SELECT firstname, lastname, invoiceid, total, customer.customerid 
FROM customer 
FULL JOIN invoice 
ON customer.customerid = invoice.customerid;
/*Create a right join that joins album and artist specifying artist name and title.*/
SELECT name, title 
FROM album 
RIGHT JOIN artist 
ON artist.artistid = album.artistid; 
/*Create a cross join that joins album and artist and sorts by artist name in ascending order*/
SELECT * 
FROM album 
CROSS JOIN artist
ORDER BY artist.NAME;
/*Perform a self-join on the employee table, joining on the reportsto column. */
SELECT * 
FROM employee 
INNER JOIN employee 
ON employee.reportsto = employee.reportsto;
/*Write a SQL Query that contains the names of all tracks that are longer than 6 minutes*/
 SELECT name 
 FROM track
 WHERE milliseconds/60000 > 6;   
/*Write a SQL Query to find the biggest song (which takes up the most space)*/
SELECT * 
FROM track
WHERE ROWNUM <= 1
ORDER BY bytes DESC;

/*Write a SQL Query that contains the titles of all albums with tracks longer than 6 minutes in them*/ 
SELECT title 
FROM album 
INNER JOIN track 
ON album.ALBUMID = track.ALBUMID
WHERE track.MILLISECONDS/60000 >= 6;
/*Write a SQL Query that contains the albumId and number of songs in the album */
Select * FROM track Order by albumid desc;

/*Write a SQL query that contains artist's names and the number of tracks they have produced (assume an artist produced a track if it appears in one of their albums)*/
    
/*Write a SQL Query that returns the most purchased media type*/

/*Write a SQL Query showing customers not in the US*/
Select * 
FROM customer
WHERE country <> 'USA';
/*Write a SQL Query showing a unique list of billing countries on the Invoice table*/
SELECT DISTINCT billingcountry FROM invoice;
/*Write a SQL Query that shows the Invoice Total, Customer Name, Country, and Sales agent for all invoices and customers*/ 
SELECT invoice.total,customer.firstname,customer.lastname,invoice.billingcountry,employee.firstname,employee.lastname
FROM customer 
INNER JOIN invoice ON invoice.customerid = customer.customerid
INNER JOIN employee ON customer.supportrepid = employee.employeeid;
/*Write a SQL Query that shows all Tracks, but displays no IDs. Should also include the Album name, Media Type, and Genre*/
SELECT track.name,album.title,mediatype.name,genre.name
From track
INNER JOIN album ON track.albumid = album.albumid
INNER JOIN mediatype ON track.mediatypeid = mediatype.mediatypeid
INNER JOIN genre ON track.genreid = genre.genreid;
/*Write a SQL Query that returns the Top 40 Songs for 2013*/

/*Write a SQL Query that shows which sales agent made the most in sales overall*/

/*Write a SQL Query that shows the top 3 best selling artists */

/*Write a SQL Query that returns which albums have no Heavy Metal tracks */
/*Write a SQL Query to find the the managers of employees supporting Brazilian customers */
SELECT employee.firstname,employee.lastname
From employee e1
INNER JOIN employee e2 ON e1.employeeid = e2.reportsto
where e1.Country = 'Brazil';