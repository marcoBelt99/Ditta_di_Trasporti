package com.beltra.sistema1.repository;

import com.beltra.sistema1.domain.LineeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;


public interface LineeRepository extends ListCrudRepository<LineeEntity, Integer> {
}
