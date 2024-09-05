package com.beltra.sistema1.repository;


import com.beltra.sistema1.domain.UtentiEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UtentiRepository extends ListCrudRepository<UtentiEntity, Integer> {

    @Override
    List<UtentiEntity> findAll();

    //UtentiEntity findDistinctTopBy();

    @Query("SELECT u FROM UtentiEntity u ORDER BY u.id DESC")
    List<UtentiEntity> findTopByOrderByIdDesc();


    @Modifying
    @Query(value = "INSERT INTO utenti (username, password) VALUES (:username, :password)", nativeQuery = true)
    void inserisciUtente(@Param("username") String username, @Param("password") String password);


    @Modifying
    @Query(value = "UPDATE utenti SET username = :username, password = :password WHERE id = :id", nativeQuery = true)
    void aggiornaUtente(int id, @Param("username") String username, @Param("password") String password);
}
