package com.adamtomaszczyk.springpetclinic.repositories;

import com.adamtomaszczyk.springpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
