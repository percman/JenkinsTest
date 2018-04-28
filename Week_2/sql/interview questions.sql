-- Write a query that will select the nth record in a table
select name, mediatypeid from (select name, mediatypeid, rownum as rn from mediatype) where rn=3;

-- Write a query that will delete duplicate entries in a table
delete from mediatype
    where rowid not in
    (select min(rowid) 
    from mediatype
    group by mediatypeid, name);
-- due friday 5pm, 27 April 2018