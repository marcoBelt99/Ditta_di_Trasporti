package com.beltra.sistema2.model.dao;


import com.beltra.sistema2.model.mo.Autobus;

import java.util.List;

public interface AutobusDAO {

    List<Autobus> insertAutoubus();
    List<Autobus> findAll();

}
