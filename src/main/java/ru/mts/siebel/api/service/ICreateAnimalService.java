package ru.mts.siebel.api.service;

import ru.mts.siebel.api.repository.IAnimal;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.model.*;
import ru.mts.siebel.service.SearchServiceImpl;
import ru.mts.siebel.util.RandomDateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICreateAnimalService {

    default Map<String, List<IAnimal>> createAnimal() throws InvalidAnimalBirthDateException {
        ISearchService searchService = new SearchServiceImpl();
        int number = 0;
        Map<String, List<IAnimal>> animals = new HashMap<>();
        while (number++ < 10) {
            AbstractAnimal animal = getAnimal(number, "Animal" + number, 1000, "Character" + number);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getClass().getSimpleName();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);
        }
        return animals;
    }
    
    default AbstractAnimal getAnimal(final int num, final String name, double cost, final String character) {
        int n = 1 + (int)(Math.random() * 5);
        String birthDate = RandomDateUtil.createRandomDate(2010, 2024).toString();
        cost *= (num + n);
        switch (n) {
            case 1:
                return new Cat(name, "Cat" + num, cost, character, birthDate);
            case 2:
                return new Dog(name, "Dog" + num, cost, character, birthDate);
            case 3:
                return new Mouse(name, "Mouse" + num, cost, character, birthDate);
            case 4:
                return new Shark(name, "Shark" + num, cost, character, birthDate);
            case 5:
                return new Wolf(name, "Wolf" + num, cost, character, birthDate);
            default:
                return null;
        }
    }

}
