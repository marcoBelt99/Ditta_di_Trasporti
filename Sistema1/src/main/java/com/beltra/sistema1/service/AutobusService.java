package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.AutobusEntity;
import com.beltra.sistema1.domain.LineeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutobusService {

    List<AutobusEntity> getListaAutobus();

    /** Per contare quanti record sono presenti nella tabella */
    long countAutobus();
}
