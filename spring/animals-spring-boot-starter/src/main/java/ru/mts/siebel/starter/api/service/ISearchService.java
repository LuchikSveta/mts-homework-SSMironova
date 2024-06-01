package ru.mts.siebel.starter.api.service;

import ru.mts.siebel.starter.model.AbstractAnimal;
import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;

public interface ISearchService {

    void checkLeapYearAnimal(AbstractAnimal animal) throws InvalidAnimalBirthDateException;

}
