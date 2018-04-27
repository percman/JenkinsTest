-- QUESTION 1: Write a query that will select the nth record in a table
-- Using chinook/p4ssw0rd

-- Get row numbers for all records over and ordered by primary key column
SELECT
  ROW_NUMBER()
  OVER (
    ORDER BY EMPLOYEEID ) AS record,
  (FIRSTNAME || LASTNAME) AS FULL_NAME,
  EMPLOYEEID
FROM EMPLOYEE;

-- Use previous query as a nested query and use where clause to find
-- the nth record based on the row number. 
-- ANSWER TO QUESTION 1
SELECT *
FROM (
  SELECT
    ROW_NUMBER()
    OVER (
      ORDER BY EMPLOYEEID ) AS record,
    (FIRSTNAME || LASTNAME) AS FULL_NAME,
    EMPLOYEEID
  FROM EMPLOYEE
)
WHERE record = 7;

-- Format
SELECT *
FROM (
  SELECT
    ROW_NUMBER()
    OVER (
      ORDER BY PK_col ) AS record_number,
    col1,
    col2, ...
  FROM EMPLOYEE
)
WHERE record_number = n;

-- QUESTION 2: Write a query that will delete duplicate entries in a table
-- Create table and insert values
CREATE TABLE duplicates (
  dupID NUMBER       NOT NULL,
  name  varchar2(20) NOT NULL,
  grade CHAR         NOT NULL,
  CONSTRAINT pk PRIMARY KEY (dupID)
);

INSERT INTO duplicates VALUES (1, 'Kirk', 'B');
INSERT INTO duplicates VALUES (2, 'Chris', 'C');
INSERT INTO duplicates VALUES (3, 'Chris', 'C');
INSERT INTO duplicates VALUES (4, 'Sara', 'A');
INSERT INTO duplicates VALUES (5, 'Sara', 'A');
INSERT INTO duplicates VALUES (6, 'Chris', 'C');
INSERT INTO duplicates VALUES (7, 'Sara', 'A');
INSERT INTO duplicates VALUES (8, 'Kirk', 'B');

-- View
SELECT *
FROM duplicates;

-- Drop when needed
DROP TABLE duplicates;

-- Find first inserted records of the duplicate records
SELECT MIN(DUPID)
FROM DUPLICATES
GROUP BY NAME, GRADE;

-- Delete all those not in the previous query, AKA the duplicates.
-- ANSWER TO QUESTION 2
DELETE DUPLICATES
WHERE DUPID NOT IN (SELECT MIN(DUPID)
                    FROM DUPLICATES
                    GROUP BY NAME, GRADE);

-- Format
DELETE table_name
WHERE PK_column NOT IN (SELECT MIN(PK_col)
                        FROM table_name
                        GROUP BY col1, col2,...);


