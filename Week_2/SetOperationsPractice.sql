SELECT albumid, title AS name FROM album
UNION
SELECT * FROM artist;


SELECT * FROM employee
MINUS
SELECT * FROM employee;