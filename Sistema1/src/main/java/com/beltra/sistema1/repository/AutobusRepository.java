package com.beltra.sistema1.repository;

import com.beltra.sistema1.domain.AutobusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;


public interface AutobusRepository extends ListCrudRepository<AutobusEntity, Integer > {

    @Override
    long count();
}
