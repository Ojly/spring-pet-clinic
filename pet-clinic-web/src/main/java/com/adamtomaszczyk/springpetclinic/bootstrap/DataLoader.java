package com.adamtomaszczyk.springpetclinic.bootstrap;

import com.adamtomaszczyk.springpetclinic.model.Owner;
import com.adamtomaszczyk.springpetclinic.model.Pet;
import com.adamtomaszczyk.springpetclinic.model.PetType;
import com.adamtomaszczyk.springpetclinic.model.Vet;
import com.adamtomaszczyk.springpetclinic.services.OwnerService;
import com.adamtomaszczyk.springpetclinic.services.PetTypeService;
import com.adamtomaszczyk.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded pet types...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("654234765");

        Pet mikesDog = new Pet();
        mikesDog.setName("Rosco");
        mikesDog.setPetType(savedDogPetType);
        mikesDog.setBirthDate(LocalDate.of(2015, 6, 15));
        mikesDog.setOwner(owner1);

        owner1.getPets().add(mikesDog);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("876345765");

        Pet fionasCat = new Pet();
        fionasCat.setName("Mimi");
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setBirthDate(LocalDate.of(2020, 9, 1));
        fionasCat.setOwner(owner2);

        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
