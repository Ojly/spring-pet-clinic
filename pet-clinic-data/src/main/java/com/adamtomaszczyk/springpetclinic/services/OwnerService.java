package com.adamtomaszczyk.springpetclinic.services;

import com.adamtomaszczyk.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
