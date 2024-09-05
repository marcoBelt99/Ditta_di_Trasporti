package com.beltra.sistema1.repository;

import com.beltra.sistema1.domain.AutistiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: anzichè estendere il CrudRepository (che usa gli Iterable), estendo il
//  ListCrudRepository (che usa il tipo List)
// https://stackoverflow.com/questions/34702252/spring-data-jpa-crudrepository-returns-iterable-is-it-ok-to-cast-this-to-list


// TODO: Questa interfaccia la uso come all'interno delle classi/interfacce relative agli Utenti
@Repository
public interface AutistiRepository extends ListCrudRepository<AutistiEntity, Integer> {

    /** TODO: (non serve implementare questo metodo) ma rispettando la giusta sintassi ci pensa Spring a
     *  fare la query.
     *  Qui sto chiedendo di trovare tutti gli autisti il cui cognome è uguale a <b>cognome</b>*.
     *
     *  Con IgnoreCase ignoro il case sensitive
     * */
    List<AutistiEntity> findAllByCognomeIgnoreCase(String cognome);


    @Modifying
    @Query(value = "INSERT INTO autisti (matricola, nome, cognome, telefono, id_utente) VALUES (:matricola, :nome, :cognome, :telefono, (select id from utenti order by id desc limit 1) )", nativeQuery = true)
    void inserisciAutista(@Param("matricola") String matricola,
                          @Param("nome") String nome,
                          @Param("cognome") String cognome,
                          @Param("telefono") String telefono
                         );

    @Modifying
    @Query(value = "UPDATE autisti SET matricola = :matricola, nome = :nome, cognome = :cognome, telefono = :telefono WHERE id_utente = :id", nativeQuery = true)
    void aggiornaAutista( int id,
                         @Param("matricola") String matricola,
                         @Param("nome") String nome,
                         @Param("cognome") String cognome,
                         @Param("telefono") String telefono);

}
