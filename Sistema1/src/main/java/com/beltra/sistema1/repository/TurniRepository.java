package com.beltra.sistema1.repository;

import com.beltra.sistema1.domain.TurniEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurniRepository extends ListCrudRepository<TurniEntity, Integer> {

}
