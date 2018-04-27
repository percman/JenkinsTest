CREATE OR REPLACE PROCEDURE deleteDuplicates
AS
BEGIN
for pokemon1 in (SELECT name, type, id from pokemon GROUP BY name,type,id) loop
  for pokemon2 in (SELECT name, type, id from pokemon GROUP BY name,type,id) loop
    If(pokemon1.id < pokemon2.id AND pokemon1.id <> pokemon2.id AND pokemon1.name = pokemon2.name AND pokemon1.type = pokemon2.type) THEN
    DELETE FROM pokemon WHERE id = pokemon2.id;
    END IF;
    end loop;
end loop;    
END;
/



CREATE OR REPLACE FUNCTION findNTHId(N NUMBER) RETURN VARCHAR2
IS 
 nthID VARCHAR2(30);

Cursor cur is
SELECT name FROM 
(SELECT * 
FROM pokemon 
WHERE rownum <= N 
ORDER BY ROWNUM DESC)
FETCH FIRST ROW ONLY;

BEGIN
open cur;
FETCH cur into nthID;
close cur;
return nthID;
END;
/