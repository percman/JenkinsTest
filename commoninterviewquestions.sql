--Write a query that will select the nth record in a table

--NTH return from result set
SELECT *
FROM(
SELECT EMPLOYEE.*, ROW_NUMBER() OVER (ORDER BY EMPLOYEEID) POSITION FROM EMPLOYEE)
WHERE POSITION = 2;


--Write a query that will delete duplicate entries in a table

--dELETING DUPLICATES
DELETE
FROM EMPLOYEE
WHERE ROWID IN(
SELECT ROWID FROM(SELECT ROWID, ROW_NUMBER()OVER (ORDER BY EMPLOYEEID) INSTANCES FROM EMPLOYEE)
WHERE INSTANCES > 1);