CREATE SEQUENCE pokemon_id_sequence
    START WITH 3
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 1000   --If we do not specify MAXVALUE, the max 999,999,999 (or something)
    NOCACHE;
    
--Option 1
BEGIN
    dbms_output.put_line(pokemon_id_sequence.nextval);
END;
/

--Option 2
EXEC dbms_output.put_line(pokemon_id_sequence.currval);

--Option 3
SELECT pokemon_id_sequence.currval FROM dual;

--Trigger which auto-increments the primary key for any INSERT made on the pokemon table
CREATE OR REPLACE TRIGGER pokemon_b_insert
    BEFORE INSERT 
    ON pokemon
    FOR EACH ROW 
    BEGIN
        IF :new.id IS NULL THEN 
            SELECT pokemon_id_sequence.nextval INTO :new.id FROM dual;
        END IF;
        dbms_output.put_line('We just inserted a pokemon with id ' || pokemon_id_sequence.currval);
    END;
    /
    
--But man oh man, it sure is gonna get old typing null EVERY time.....
INSERT INTO pokemon VALUES(null, 'Pikachu', 'Electric');

SELECT * FROM pokemon ORDER BY id;

--Creating a stored procedure to insert pokemon
CREATE OR REPLACE PROCEDURE insert_pokemon(poke_name IN VARCHAR2, poke_type IN VARCHAR2)
AS
BEGIN
--    INSERT INTO pokemon VALUES (null, poke_name, poke_type);
      INSERT INTO pokemon (name, type, id) VALUES (poke_name, poke_type, null);
      COMMIT;
END;
/

--Transaction to delete a pokemon
CREATE OR REPLACE PROCEDURE delete_pokemon(poke_id IN NUMBER)
AS
BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    DELETE FROM pokemon WHERE id = poke_id;
    COMMIT;
END;
/

--My personal go-to for running SP
BEGIN
    delete_pokemon(9);
END;
/

SELECT * FROM pokemon ORDER BY id;


--Functions MUST RETURN A VALUE
CREATE OR REPLACE FUNCTION get_current_time RETURN TIMESTAMP
AS
    time_right_now TIMESTAMP;
BEGIN
    SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
    dbms_output.put_line('The current time is ' || time_right_now);
    return time_right_now;
END;
/





