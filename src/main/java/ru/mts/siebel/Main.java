package ru.mts.siebel;

import ru.mts.siebel.api.repository.IAnimal;
import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.repository.AnimalsRepositoryImpl;
import ru.mts.siebel.service.CreateAnimalServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        try {
            IAnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
            Map<String, List<IAnimal>> animals = createAnimalService.createAnimal(6);
            animalsRepository.setAnimalsFromMap(animals);
            System.out.println(animals);

            Map<String, LocalDate> animalsLeapYear = animalsRepository.findLeapYearNames();
            if (animalsLeapYear.size() == 0) {
                System.out.println("\nВсе животные родились не в високосный год");
            } else {
                System.out.println("\nЖивотные, которые родились в високосный год:\n" + animalsLeapYear);
            }
            System.out.println("\nЖивотные, возраст которых старше 5 лет:\n" + animalsRepository.findOlderAnimal(5));
            System.out.println("\nКоличество дубликатов животных:\n" + animalsRepository.findDuplicate());
        } catch (final InvalidAnimalBirthDateException e) {
            throw new RuntimeException("При вызове метода произошла ошибка\n" + e);
        }
    }

}
