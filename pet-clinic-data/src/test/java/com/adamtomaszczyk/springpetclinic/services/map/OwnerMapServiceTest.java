package com.adamtomaszczyk.springpetclinic.services.map;

import com.adamtomaszczyk.springpetclinic.model.Owner;
import com.adamtomaszczyk.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    OwnerService ownerService;

    final Long OWNER_ID = 1L;
    final String OWNER_LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        ownerService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerService.save(Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(OWNER_ID);

        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void saveExistingId() {
        long ownerId = 2L;
        Owner owner = Owner.builder().id(ownerId).build();
        Owner savedOwner = ownerService.save(owner);

        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = Owner.builder().build();
        Owner savedOwner = ownerService.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(OWNER_ID));

        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void deleteByExistingId() {
        ownerService.deleteById(OWNER_ID);

        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void deleteByNotExistingId() {
        Long ownerId = 2L;
        ownerService.deleteById(ownerId);

        assertEquals(1, ownerService.findAll().size());
    }

    @Test
    void findByExistingLastName() {
        Owner owner = ownerService.findByLastName(OWNER_LAST_NAME);

        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        assertEquals(OWNER_LAST_NAME, owner.getLastName());
    }

    @Test
    void findByNotExistingLastName() {
        String ownerLastName = "Kowalski";
        Owner owner = ownerService.findByLastName(ownerLastName);

        assertNull(owner);
    }
}