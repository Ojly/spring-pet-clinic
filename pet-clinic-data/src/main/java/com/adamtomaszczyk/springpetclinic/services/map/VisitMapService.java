package com.adamtomaszczyk.springpetclinic.services.map;

import com.adamtomaszczyk.springpetclinic.model.Visit;
import com.adamtomaszczyk.springpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return null;
    }

    @Override
    public Visit findById(Long id) {
        return null;
    }

    @Override
    public Visit save(Visit object) {
        return null;
    }

    @Override
    public void delete(Visit object) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
