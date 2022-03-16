package com.adamtomaszczyk.springpetclinic.repositories;

import com.adamtomaszczyk.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
