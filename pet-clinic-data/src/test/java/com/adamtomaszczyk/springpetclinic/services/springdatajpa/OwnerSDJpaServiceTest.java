package com.adamtomaszczyk.springpetclinic.services.springdatajpa;

import com.adamtomaszczyk.springpetclinic.model.Owner;
import com.adamtomaszczyk.springpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    final Long OWNER_ID = 1L;
    final String OWNER_LAST_NAME = "Smith";
    Owner expectedOwner;

    @BeforeEach
    void setUp() {
        expectedOwner = Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> expectedOwners = new HashSet<>();
        expectedOwners.add(Owner.builder().id(2L).build());
        expectedOwners.add(Owner.builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(expectedOwners);

        Set<Owner> actualOwners = ownerService.findAll();

        assertArrayEquals(expectedOwners.toArray(), actualOwners.toArray());

        verify(ownerRepository).findAll();
    }

    @Test
    void findByExistingId() {
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.of(expectedOwner));

        Owner actualOwner = ownerService.findById(OWNER_ID);

        assertEquals(expectedOwner, actualOwner);
        verify(ownerRepository).findById(OWNER_ID);
    }

    @Test
    void findByNotExistingId() {
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.empty());

        Owner actualOwner = ownerService.findById(OWNER_ID);

        assertNull(actualOwner);
        verify(ownerRepository).findById(OWNER_ID);
    }

    @Test
    void save() {
        when(ownerRepository.save(expectedOwner)).thenReturn(expectedOwner);

        Owner actualOwner = ownerService.save(expectedOwner);

        assertEquals(expectedOwner, actualOwner);
        verify(ownerRepository).save(expectedOwner);
    }

    @Test
    void delete() {
        ownerService.delete(expectedOwner);

        verify(ownerRepository).delete(expectedOwner);
    }

    @Test
    void deleteById() {
        ownerService.deleteById(OWNER_ID);

        verify(ownerRepository).deleteById(OWNER_ID);
    }

    @Test
    void findByExistingLastName() {
        when(ownerRepository.findByLastName(OWNER_LAST_NAME)).thenReturn(Optional.of(expectedOwner));

        Owner actualOwner = ownerService.findByLastName(OWNER_LAST_NAME);

        assertEquals(expectedOwner, actualOwner);

        verify(ownerRepository).findByLastName(OWNER_LAST_NAME);
    }

    @Test
    void findByNotExistingLastName() {
        when(ownerRepository.findByLastName(OWNER_LAST_NAME)).thenReturn(Optional.empty());

        Owner actualOwner = ownerService.findByLastName(OWNER_LAST_NAME);

        assertNull(actualOwner);

        verify(ownerRepository).findByLastName(OWNER_LAST_NAME);
    }
}