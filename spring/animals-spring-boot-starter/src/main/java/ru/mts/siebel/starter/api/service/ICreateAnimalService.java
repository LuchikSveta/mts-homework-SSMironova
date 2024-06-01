package ru.mts.siebel.starter.api.service;

import ru.mts.siebel.starter.api.model.IAnimal;
import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;

import java.util.List;
import java.util.Map;

public interface ICreateAnimalService {

    Map<String, List<IAnimal>> createAnimal() throws InvalidAnimalBirthDateException;

    Map<String, List<IAnimal>> createAnimal(int number) throws InvalidAnimalBirthDateException;

}
