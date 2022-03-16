package com.adamtomaszczyk.springpetclinic.repositories;

import com.adamtomaszczyk.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
