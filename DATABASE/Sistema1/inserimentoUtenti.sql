INSERT INTO public.autisti(
	matricola, cognome, nome, telefono, id_utente)
	VALUES ('12125', 'Bonfe', 'Marcello', '3366887441', (select id from utenti order by id desc limit 1) );
	
	select id from utenti order by id desc limit 1
	
	select * from autisti	
	
	select * from utenti left join autisti autisti2 ON autisti2.id_utente = utenti.id