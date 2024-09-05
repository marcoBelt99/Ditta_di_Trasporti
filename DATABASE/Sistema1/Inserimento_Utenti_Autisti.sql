INSERT INTO public."Utenti"(
	"Username", "Password", "Ruolo")
	VALUES ('paz', '123passwordce', 'AUTISTA');

INSERT INTO public."Autisti"(
	"Matricola", "Cognome", "Nome", "Telefono", "ID_Utente")
	VALUES ('12347', 'Ghiraldello', 'Filippo', '3697414522', 
			(SELECT "Utenti"."ID" FROM "Utenti" ORDER BY "Utenti"."ID" DESC limit 1) );



SELECT * FROM "Autisti"
SELECT * FROM "Utenti"
