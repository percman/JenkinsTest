-- how to create a sequence 
CREATE SEQUENCE pokemon_id_sequence
    START WITH 2
    INCREMENT BY 1 
    MINVALUE 0 
    MAXVALUE 1000 --if not specified, the max is something around 999,999,999 
    NOCACHE;
    
-- prints out the   
EXEC dbms_output.put_line(pokemon_id_sequence.currval);


CREATE OR REPLACE TRIGGER pokemon_b_insert 
    BEFORE INSERT 
    ON pokemon 
    FOR EACH ROW 
    BEGIN 
        IF :new.id IS NULL THEN 
            SELECT pokemon_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
    END;
    /
    
    
CREATE OR REPLACE PROCEDURE insert_pokemon(poke_name IN VARCHAR2, poke_type IN VARCHAR2)
AS 
BEGIN 
    --INSERT INTO pokemon VALUES (null, poke_name, poke_type);
    INSERT INTO pokemon (name, type, id) VALUES (poke_name, poke_type, null);
    COMMIT;
END; 
/

CREATE OR REPLACE PROCEDURE delete_pokemon(poke_id IN NUMBER)
AS 
BEGIN
    DELETE FROM pokemon WHERE id = poke_id;
    COMMIT;
END;
/


----------------------------------------------------
--Write a query that will select the nth record in a table

SELECT name, row_num FROM (
    SELECT name, ROW_NUMBER() 
    OVER (ORDER BY id) row_num 
    FROM pokemon)
WHERE row_num = 3;
----------------------------------------------------

----------------------------------------------------
--Write a query that will delete duplicate entries in a table

DELETE FROM pokemon 
WHERE id NOT IN (
    SELECT MIN(id)
    FROM pokemon
    GROUP BY name, type);
----------------------------------------------------





