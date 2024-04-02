package ru.mts.siebel.service;

import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.exception.InvalidAnimalException;
import ru.mts.siebel.api.service.ISearchService;
import ru.mts.siebel.model.AbstractAnimal;

public class SearchServiceImpl implements ISearchService {

    @Override
    public void checkLeapYearAnimal(final AbstractAnimal animal) throws InvalidAnimalBirthDateException {
        if (animal == null) {
            throw new InvalidAnimalException();
        }
        if (animal.getBirthDate() == null) {
            throw new InvalidAnimalBirthDateException("У животного " + animal.getClassName().toUpperCase() + " не указана дата его рождения");
        }
        if (animal.getBirthDate().isLeapYear()) {
            System.out.println(animal.getName().toUpperCase() + " был рожден в високосный год");
        } else {
            System.out.println(animal.getName().toUpperCase() + " не был рожден в високосный год");
        }
    }

}
