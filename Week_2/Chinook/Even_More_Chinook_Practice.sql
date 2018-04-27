-- Create a function that returns the returns the current time 
create or replace function current_time return timestamp
as
    time_right_now timestamp;
begin
    select current_timestamp into time_right_now from dual;
    dbms_output.put_line('The current time is ' || time_right_now);
    return time_right_now;
end;
/

-- Create a function that returns the length of a mediatype from the mediatype table
select * from mediatype;

-- Create a function that returns the average total of all invoices


-- Create a function that returns the average price of invoiceline items in the invoiceline table 

-- Create a function that returns all employees born after 1968

-- Create a stored procedure that selects the first and last names from the employee table 

-- Create a stored procedure that returns the name and company of a customer 

-- Create a transaction that given an invoice id will delete that invoice 

-- Create a transaction that inserts a new record into the Customer table

-- Create an after trigger on the employee table that executes after a record is inserted 

-- Create an after trigger on the customer table that executes after a record is inserted 

-- Create an before trigger on the employee table that executes after a record is inserted 

-- Create a audit table and delete trigger on the customer table before a record is deleted 