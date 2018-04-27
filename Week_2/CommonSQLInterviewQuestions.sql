--
-- Due Friday 500PM
--


-- Write a query that will select the nth record in a table
/* 
SELECT * FROM 
    (SELECT trackid, name, albumid, mediatypeid, genreid, composer, milliseconds, bytes, unitprice, 
        ROW_NUMBER() OVER (ORDER BY track.trackid) R FROM track)
        -- The select inside the parens must explicitly say the column names, it cannot use the star
    WHERE R = 23; 
*/    

SELECT id, name, type FROM (
    SELECT id, name, type FROM pokemon
        WHERE ROWNUM < 7 /* this is the nth value */
        ORDER BY id
    )
    WHERE ROWNUM < 2
    ORDER BY id DESC;


-- Write a query that will delete duplicate entries in a table
DELETE FROM pokemon p
    WHERE id NOT IN(
        SELECT MIN(id) FROM pokemon pk
        WHERE p.name = pk.name
        AND p.type = pk.type);  -- this will delete the duplicates but keep the duplicate pokemon with the lowest id
                                -- which should be the first of that pokemon which was inserted because of how we set up id
            
    
    