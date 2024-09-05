package com.beltra.sistema2.model.dao;

import com.beltra.sistema2.model.mo.Autista;

import java.util.List;

public interface AutistaDAO {

    List<Autista> insertAutisti();
    List<Autista> findAll();

}
