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
            AbstractAnimal animal = getAnimal(number, 1000, "Character" + number);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getClassName();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);
        }
        return animals;
    }
    
    default AbstractAnimal getAnimal(final int num, double cost, final String character) {
        int n = (int)(Math.random() * 5);
        String[] name = new String[] { "Мурзик", "Печенька", "Белый клык", "Свитти", "Марсик" };
        String birthDate = RandomDateUtil.createRandomDate(2010, 2024).toString();
        cost *= (num + n);
        switch (n) {
            case 0:
                return new Cat(name[n] + n, "Cat" + num, cost, character, birthDate);
            case 1:
                return new Dog(name[n] + n, "Dog" + num, cost, character, birthDate);
            case 2:
                return new Mouse(name[n] + n, "Mouse" + num, cost, character, birthDate);
            case 3:
                return new Shark(name[n] + n, "Shark" + num, cost, character, birthDate);
            case 4:
                return new Wolf(name[n] + n, "Wolf" + num, cost, character, birthDate);
            default:
                return null;
        }
    }

}
