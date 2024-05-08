package ru.mts.siebel.service;

import ru.mts.siebel.api.model.IAnimal;
import ru.mts.siebel.api.service.IAnimalFileService;
import ru.mts.siebel.api.service.ICreateAnimalService;
import ru.mts.siebel.api.service.ISearchService;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.model.AbstractAnimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImpl implements ICreateAnimalService {

    public Map<String, List<IAnimal>> createAnimalFromParent() throws InvalidAnimalBirthDateException {
        return ICreateAnimalService.super.createAnimal();
    }

    @Override
    public Map<String, List<IAnimal>> createAnimal() throws InvalidAnimalBirthDateException {
        ISearchService searchService = new SearchServiceImpl();
        int number = 1;
        Map<String, List<IAnimal>> animals = new HashMap<>();
        do {
            AbstractAnimal animal = getAnimal(number, 1000 * number, "Character" + number);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getType();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);
        } while (number++ < 10);

        return animals;
    }

    public Map<String, List<IAnimal>> createAnimal(final int number) throws InvalidAnimalBirthDateException {
        ISearchService searchService = new SearchServiceImpl();
        Map<String, List<IAnimal>> animals = new HashMap<>();
        for (int i = 1; i < number + 1; i++) {
            AbstractAnimal animal = getAnimal(i, 1000 * i, "Character" + i);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getType();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);

            IAnimalFileService.logAnimals(i, animal);
        }
        return animals;
    }

    @Override
    public AbstractAnimal getAnimal(final int n, final double cost, final String character) {
        return ICreateAnimalService.super.getAnimal(n, cost, character);
    }

}
