package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.AutobusEntity;
import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.repository.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutobusServiceImpl implements AutobusService{

    @Autowired
    AutobusRepository autobusRepository;

    @Override
    public List<AutobusEntity> getListaAutobus() {
        return autobusRepository.findAll();
    }

    public long countAutobus() {
        return autobusRepository.count();
    }
}
