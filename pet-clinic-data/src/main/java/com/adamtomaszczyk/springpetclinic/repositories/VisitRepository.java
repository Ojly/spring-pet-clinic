package com.adamtomaszczyk.springpetclinic.repositories;

import com.adamtomaszczyk.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
