package ru.mts.siebel.starter.service;

import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.starter.exception.InvalidAnimalException;
import ru.mts.siebel.starter.api.service.ISearchService;
import ru.mts.siebel.starter.model.AbstractAnimal;

public class SearchServiceImpl implements ISearchService {

    @Override
    public void checkLeapYearAnimal(final AbstractAnimal animal) throws InvalidAnimalBirthDateException {
        if (animal == null) {
            throw new InvalidAnimalException();
        }
        if (animal.getBirthDate() == null) {
            throw new InvalidAnimalBirthDateException("У животного " + animal.getType().toUpperCase() + " не указана дата его рождения");
        }
        if (animal.getBirthDate().isLeapYear()) {
            System.out.println(animal.getName().toUpperCase() + " был рожден в високосный год");
        } else {
            System.out.println(animal.getName().toUpperCase() + " не был рожден в високосный год");
        }
    }

}
