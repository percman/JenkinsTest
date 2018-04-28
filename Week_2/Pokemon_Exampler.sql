CREATE TABLE pokemon(
--col-name COL_TYPE
id NUMBER(6),
name VARCHAR2(60),
type VARCHAR2(10),
CONSTRAINT PK_POKEMON PRIMARY KEY (id)
);

CREATE SEQUENCE pokemon_id_sequence
    START WITH 4
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 1000       -- if we do not specify MAXVALUE, the max is 999, 999, 999 (or something)
    NOCACHE;

    
-- Option 1
BEGIN
    dbms_output.put_line(pokemon_id_sequence.nextval);
END;
/


-- Option 2
EXEC dbms_output.put_line(pokemon_id_sequence.currval);


-- Option 3
SELECT pokemon_id_sequence.currval FROM dual;


-- Trigger which auto-increments the primary key for any INSERT made on the pokemon table
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
    
    
-- But man oh man it sure is gonna get old typing null EVERY time.......
INSERT INTO pokemon VALUES(null, 'Tentacruel', 'Water');

SELECT * FROM pokemon;

-- Creating a stored procedure to insert pokemon
CREATE OR REPLACE PROCEDURE insert_pokemon(poke_name IN VARCHAR2, poke_type IN VARCHAR2)
    AS
    BEGIN
        -- INSERT INTO pokemon VALUES (null, poke_name, poke_type);
        INSERT INTO pokemon (name, type, id) VALUES (poke_name, poke_type, null);
        COMMIT;
    END;
    /
    
    
-- Transaction to delete pokemon
CREATE OR REPLACE PROCEDURE delete_pokemon(poke_id IN NUMBER)
    AS
    BEGIN
        DELETE FROM pokemon WHERE id = poke_id;
        COMMIT;
    END;
    /

-- William's go to for running SP
BEGIN
    insert_pokemon('Pikachu', 'Electric');
END;
/

SELECT * FROM pokemon ORDER BY id;


-- Functions MUST RETURN A VALUE
CREATE OR REPLACE FUNCTION get_current_time RETURN TIMESTAMP
    AS
        time_right_now TIMESTAMP;
    BEGIN
        SELECT CURRENT_TIMESTAMP INTO time_right_now FROM dual;
        dbms_output.put_line('The current time is ' || time_right_now);
        RETURN time_right_now;
    END;
    /

SELECT get_current_time FROM dual;


-- Write a function that returns a cursor for all pokemon of that type
CREATE OR REPLACE FUNCTION pokemon_by_type(poke_type IN VARCHAR2) RETURN SYS_REFCURSOR
    AS
        poke_cursor SYS_REFCURSOR;
    BEGIN
        OPEN poke_cursor FOR 
            SELECT id, name, type FROM pokemon
            WHERE type = poke_type;
        RETURN poke_cursor;
    END;
    /

DECLARE
    my_cursor SYS_REFCURSOR;
    poke_id NUMBER(10);
    poke_name VARCHAR2(60);
    poke_type VARCHAR2(60);
BEGIN
    my_cursor := pokemon_by_type('Electric');
    LOOP
        FETCH my_cursor INTO poke_id, poke_name, poke_type;
        EXIT WHEN my_cursor%NOTFOUND;
        dbms_output.put_line(poke_id || ': ' || poke_name || ' (' || poke_type || ')');
    END LOOP;
    CLOSE my_cursor;
END;
/


SELECT extractvalue(column_value, '/ROW/poke_id') poke_id,
        extractvalue(column_value, '/ROW/poke_name') poke_name,
        extractvalue(column_value, '/ROW/poke_type') poke_type
FROM TABLE(xmlsequence(pokemon_by_type('Electric')));

SELECT pokemon_by_type('Electric') FROM dual;
