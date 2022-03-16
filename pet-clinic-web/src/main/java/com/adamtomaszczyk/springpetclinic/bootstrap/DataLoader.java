package com.adamtomaszczyk.springpetclinic.bootstrap;

import com.adamtomaszczyk.springpetclinic.model.*;
import com.adamtomaszczyk.springpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");
        Speciality savedRadiology = specialtyService.save(radiology);
        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");
        Speciality savedSurgery = specialtyService.save(surgery);
        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        System.out.println("Loaded specialties...");

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
        vet1.getSpecialities().add(savedRadiology);
        vet1.getSpecialities().add(savedDentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded vets...");

        Visit dogVisit1 = new Visit();
        dogVisit1.setDescription("Regular checkup");
        dogVisit1.setDate(LocalDate.now().minusDays(30));

        Visit dogVisit2 = new Visit();
        dogVisit2.setDescription("Regular checkup");
        dogVisit2.setDate(LocalDate.now().minusDays(60));

        mikesDog.addVisit(dogVisit1);
        mikesDog.addVisit(dogVisit2);

        Visit catVisit1 = new Visit();
        catVisit1.setDescription("Regular checkup");
        catVisit1.setDate(LocalDate.now().minusDays(20));

        Visit catVisit2 = new Visit();
        catVisit2.setDescription("Regular checkup");
        catVisit2.setDate(LocalDate.now().minusDays(50));

        fionasCat.addVisit(catVisit1);
        fionasCat.addVisit(catVisit2);

        petService.save(mikesDog);
        petService.save(fionasCat);

        System.out.println("Loaded visits...");
    }
}
