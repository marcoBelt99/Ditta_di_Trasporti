-- Elenco di tutti i turni
select * from turni order by id_utente;

-- Elenco di tutti autisti
select autisti.id_utente, autisti.nome, autisti.cognome from autisti order by id_utente

-- Elenco di tutti gli autisti a cui è assegnato un turno ==> AUTISTI IMPEGNATI
select autisti.id_utente, autisti.nome, autisti.cognome, turni.data, turni.ora_inizio, turni.ora_fine
	from autisti, turni
		where autisti.id_utente = turni.id_utente order by autisti.id_utente

-- Elenco di tutti gli autisti a cui NON è assegnato un turno ==> AUTISTI DISPONIBILI
select distinct autisti.id_utente, autisti.nome, autisti.cognome
	from autisti, turni
		where autisti.id_utente not in
				(select autisti.id_utente
					from autisti, turni
						where autisti.id_utente = turni.id_utente)
						order by autisti.id_utente

-- Elenco di tutti gli autisti che sono in turno in determinato periodo
select autisti.id_utente, autisti.nome, autisti.cognome, turni.data, turni.ora_inizio, turni.ora_fine
	from autisti, turni
		where autisti.id_utente = turni.id_utente and turni.data between '2024-05-27' and '2024-05-28'
		
		
-- Elenco di tutti gli autisti che sono turno (in servizio) in un determinato giorno
select autisti.id_utente, autisti.nome, autisti.cognome, turni.data, turni.ora_inizio, turni.ora_fine
	from autisti, turni
		where autisti.id_utente = turni.id_utente and turni.data =  '2024-05-27'
		
-- Elenco di tutti gli autisti che sono in turno (in servizio) in una determinata fascia oraria
select autisti.id_utente, autisti.nome, autisti.cognome, turni.data, turni.ora_inizio, turni.ora_fine
	from autisti, turni
		where autisti.id_utente = turni.id_utente
			and ( (turni.ora_inizio >= '13:00:00') 
				 and (turni.ora_fine >= '13:00' and turni.ora_fine <= '22:00:00'))


-- Storico dei turni svolti da un determinato autista
select * 
	from autisti, turni 
		where autisti.id_utente = turni.id_utente 
			and turni.id_utente = 2