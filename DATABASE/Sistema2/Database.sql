CREATE DATABASE IF NOT EXISTS sistema2db;


-- CREATE TABLE IF NOT EXISTS autista (
--      codice VARCHAR(7) UNIQUE PRIMARY KEY ,
--      matricola VARCHAR(10) NOT NULL UNIQUE,
--      nome VARCHAR(40) NOT NULL,
--      cognome VARCHAR(40) NOT NULL,
--      telefono VARCHAR(10) NOT NULL
--  );
-- 
-- 
-- 
-- CREATE TABLE IF NOT EXISTS autobus (
--     codice VARCHAR(7) UNIQUE PRIMARY KEY,
--     targa VARCHAR(10) NOT NULL UNIQUE,
--     modello VARCHAR(50) NOT NULL,
--     capienza INT NOT NULL
-- );
-- 
-- 
-- CREATE TABLE IF NOT EXISTS turno (
--     id INT PRIMARY KEY, -- ho tolto id autoincrement per evitare possibili problemi con gli inserimenti in xml
--     data DATE NOT NULL,
--     ora_inizio TIME NOT NULL,
--     ora_fine TIME NOT NULL,
--     codice_autista VARCHAR(7) NOT NULL,
--     codice_autobus VARCHAR(7) NOT NULL,
--     FOREIGN KEY (codice_autista) REFERENCES autista(codice) ON DELETE CASCADE,
--     FOREIGN KEY (codice_autobus) REFERENCES autobus(codice) ON DELETE CASCADE
-- );

CREATE TABLE IF NOT EXISTS retribuzione (
    id INT PRIMARY KEY NOT NULL,
    importo DOUBLE NOT NULL,
    mese VARCHAR(15) NOT NULL,
    codice_autista VARCHAR(7) UNIQUE NOT NULL
);

truncate retribuzione ;


-- Elenco di tutte le retribuzioni
select * from retribuzione;

-- Elenco delle retribuzioni di un determinato autista in tutti i mesi
select id, mese, importo  from retribuzione where codice_autista ="A002" group by id;


-- Elenco delle retribuzioni di un determinato autista in un determinato mese
select * from retribuzione where codice_autista ="A002" and mese="giugno";

-- Somma di tutte le retribuzioni di un determinato autista
select SUM(importo)  from retribuzione where codice_autista ="A002";

-- Somma delle retribuzioni degli autisti, raggruppando per codice_autista
select codice_autista , SUM(importo) as IMPORTO from retribuzione group by codice_autista ;


-- Ottieni il valore massimo delle somme delle retribuzioni annue di autista
select MAX(IMPORTO)
from (
    select codice_autista, SUM(importo) as IMPORTO
    from retribuzione
    group by codice_autista
) as MASSIMO 

 	



-- Ottieni il codice_autista con la somma massima delle retribuzioni e il valore massimo
SELECT s.codice_autista, s.IMPORTO
FROM (
    SELECT codice_autista, SUM(importo) AS IMPORTO
    FROM retribuzione
    GROUP BY codice_autista
) AS s
JOIN (
    SELECT MAX(IMPORTO) AS MAX_IMPORTO
    FROM (
        SELECT SUM(importo) AS IMPORTO
        FROM retribuzione
        GROUP BY codice_autista
    ) AS somme
) AS max_somme
ON s.IMPORTO = max_somme.MAX_IMPORTO;