-- Write a query that will select the nth record in a table
SELECT * FROM (
SELECT id, name, type, ROW_NUMBER() OVER (ORDER BY id) R FROM pokemon)
WHERE R = 5;

-- Write a query that will delete duplicate entries in a table
