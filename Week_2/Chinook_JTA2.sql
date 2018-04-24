SELECT * FROM employee WHERE FirstName = 'Andrew' AND REPORTSTO IS NULL;

SELECT * FROM album ORDER BY title DESC;

SELECT * FROM customer where customerid = '32';
UPDATE customer SET firstName = 'Robert' , lastName = 'Walter' WHERE customerid = '32';

SELECT * FROM employee WHERE hiredate BETWEEN to_date('06-01-2003', 'mm-dd-yyyy') and to_date('03-01-2004', 'mm-dd-yyyy');

DELETE customer WHERE firstName = 'Robert' AND lastName = 'Walter';

SELECT * FROM JOIN customer, employee WHERE firstName = 'Robert' AND lastName = 'Walter';

SELECT * FROM invoice;
SELECT * FROM OUTER JOIN customer ON customer.customerid = invoice.customerid;