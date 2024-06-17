package ru.mts.siebel.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.siebel.springdata.entity.AnimalType;
import ru.mts.siebel.springdata.repository.AnimalTypeRepository;

@Service
public class AnimalTypeService {

    @Autowired
    private AnimalTypeRepository repository;

    public AnimalType findAnimalType(final String animalType) {
        return repository.findByType(animalType);
    }

    public AnimalType save(final AnimalType animalType) {
        return repository.save(animalType);
    }

}
