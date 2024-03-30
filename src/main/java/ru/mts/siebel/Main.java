package ru.mts.siebel;

import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.repository.AnimalsRepositoryImpl;
import ru.mts.siebel.service.CreateAnimalServiceImpl;

public class Main {

    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        try {
            IAnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
            animalsRepository.setAnimalsMap(createAnimalService.createAnimal(6));
            System.out.println(animalsRepository.getMap());
            System.out.println("\nЖивотные, которые родились в високосный год:\n" + animalsRepository.findLeapYearNames());
            System.out.println("\nЖивотные, возраст которых старше 5 лет:\n" + animalsRepository.findOlderAnimal(5));
            System.out.println("\nКоличество дубликатов животных:\n" + animalsRepository.findDuplicate());
        } catch (final InvalidAnimalBirthDateException e) {
            throw new RuntimeException("При вызове метода произошла ошибка\n" + e);
        }
    }

}
