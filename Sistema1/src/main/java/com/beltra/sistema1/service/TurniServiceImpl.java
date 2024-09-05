package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.AutistiEntity;
import com.beltra.sistema1.domain.TurniEntity;
import com.beltra.sistema1.repository.AutistiRepository;
import com.beltra.sistema1.repository.TurniRepository;
import com.beltra.sistema1.utils.Stringhe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TurniServiceImpl implements TurniService {

    @Autowired
    TurniRepository turniRepository;

    @Autowired
    AutistiRepository autistiRepository;

    @Override
    public List<TurniEntity> getListaTurni() {
        return turniRepository.findAll();
    }

    @Override
    public List<TurniEntity> getListaTurniAutistaById(int id_utente) {
        return turniRepository
                .findAll()
                .stream()
                .filter( turniEntity -> turniEntity.getIdUtente() == id_utente )
                .toList();
    }

    public List<TurniEntity> getListaTurniAutistaByIdFilterByMese(int id_utente, String mese) {
        return getListaTurniAutistaById(id_utente)
                .stream()
                .filter( turniEntity -> turniEntity.getData().getMonth() == Stringhe.mesiMap.get(mese) )
                .toList();
    }


    @Override
    public void inserisciTurno(TurniEntity turno) {
        turniRepository.save(turno);
    }


    public void inserisciBatchTurni(List<TurniEntity> listaTurni) {
        turniRepository.saveAll(listaTurni);
    }


    @Override
    @Transactional
    public void cancellaTuttiTurniUtente(int id) {
        turniRepository
            .findAll()  // Ottengo la lsita di tutti i turni da DB
            .stream()   // Applico la stream API
            .filter( turno -> turno.getIdUtente() == id )// Seleziono solo i turni che si riferiscono a quel determinato utente
            .toList() // Raccolgo lo stream filtrato in una Lista
            .forEach(
                    turno -> turniRepository.delete( turno ) // Elimino a DB ogni turno di tale lista
            );

    }
}
