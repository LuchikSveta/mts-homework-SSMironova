package ru.mts.siebel.starter.service;

import org.springframework.beans.factory.annotation.Autowired;

import ru.mts.siebel.starter.api.model.IAnimal;
import ru.mts.siebel.starter.api.service.ICreateAnimalService;
import ru.mts.siebel.starter.api.service.IFileAnimalService;
import ru.mts.siebel.starter.api.service.ISearchService;
import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.starter.model.*;
import ru.mts.siebel.starter.util.RandomDateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImpl implements ICreateAnimalService {

    @Autowired
    IFileAnimalService fileService;
    @Autowired
    NameAnimalService namesService;
    @Autowired
    ISearchService searchService;

    void checkFiles() {
        fileService.clearFile();
        fileService.generateSecretInformation();
    }

    public Map<String, List<IAnimal>> createAnimal() throws InvalidAnimalBirthDateException {
        checkFiles();
        int number = 0;
        Map<String, List<IAnimal>> animals = new HashMap<>();
        while (number++ < 10) {
            AbstractAnimal animal = this.getAnimal(number);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getType();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);

            fileService.logAnimals(number, animal);
        }
        return animals;
    }

    public Map<String, List<IAnimal>> createAnimal(final int number) throws InvalidAnimalBirthDateException {
        checkFiles();
        Map<String, List<IAnimal>> animals = new HashMap<>();
        for (int i = 1; i < number + 1; i++) {
            AbstractAnimal animal = getAnimal(i);
            searchService.checkLeapYearAnimal(animal);
            String animalType = animal.getType();
            animals.computeIfAbsent(animalType, k -> new ArrayList<>());
            animals.get(animalType).add(animal);
            System.out.println(animal);

            fileService.logAnimals(i, animal);
        }
        return animals;
    }

    public AbstractAnimal getAnimal(final int i) {
        int n = (int) (Math.random() * 5);
        int randomNum = 1 + (int) (Math.random() * 100);
        List<List<String>> namesList = new ArrayList<>(List.of(namesService.getCat(), namesService.getDog(), namesService.getMouse(), namesService.getShark(), namesService.getWolf()));
        String name = namesList.get(n).get((int) (Math.random() * namesList.get(n).size())) + "_" + randomNum;

        String birthDate = RandomDateUtil.createRandomDate(2010, 2024).toString();
        double cost = Math.random() * 1000;
        String character = "Character" + i;

        switch (n) {
            case 0:
                return new Cat(name, "Cat" + i, cost, character, birthDate, fileService.getSecretInformation());
            case 1:
                return new Dog(name, "Dog" + i, cost, character, birthDate, fileService.getSecretInformation());
            case 2:
                return new Mouse(name, "Mouse" + i, cost, character, birthDate, fileService.getSecretInformation());
            case 3:
                return new Shark(name, "Shark" + i, cost, character, birthDate, fileService.getSecretInformation());
            case 4:
                return new Wolf(name, "Wolf" + i, cost, character, birthDate, fileService.getSecretInformation());
            default:
                return null;
        }
    }

}
