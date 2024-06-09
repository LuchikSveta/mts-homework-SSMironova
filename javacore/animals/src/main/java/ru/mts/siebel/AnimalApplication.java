package ru.mts.siebel;

import ru.mts.siebel.api.model.IAnimal;
import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.api.service.IAnimalFileService;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.repository.AnimalsRepositoryImpl;
import ru.mts.siebel.service.CreateAnimalServiceImpl;
import ru.mts.siebel.service.ResultReader;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AnimalApplication {

    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        try {
            IAnimalFileService.clearFile();
            IAnimalFileService.generateSecretInformation();

            IAnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
            Map<String, List<IAnimal>> animals = createAnimalService.createAnimal(6);
            animalsRepository.setAnimalsFromMap(animals);
            System.out.println("\nВсе животные:\n" + animals);

            Map<String, LocalDate> animalsLeapYear = animalsRepository.findLeapYearNames();
            if (animalsLeapYear.size() == 0) {
                System.out.println("\nВсе животные родились не в високосный год");
            } else {
                System.out.println("\nЖивотные, которые родились в високосный год:\n" + animalsLeapYear);
            }

            int n = 5;
            System.out.println("\nЖивотные, возраст которых старше " + n + " лет:\n" + animalsRepository.findOlderAnimal(n));

            System.out.println("\nДубликаты животных:\n" + animalsRepository.findDuplicate());

            System.out.println("\nCредний возраст всех животных: " + animalsRepository.findAverageAge());

            List<IAnimal> animalsOldAndExpensive = animalsRepository.findOldAndExpensive();
            if (animalsOldAndExpensive.size() == 0) {
                System.out.println("\nНет таких животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных");
            } else {
                System.out.println("\nЖивотные, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных:\n" + animalsOldAndExpensive);
            }

            System.out.println("\n3 животных с самой низкой ценой: \n" + animalsRepository.findMinCostAnimals());

            ResultReader reader = new ResultReader();
            reader.readAnimalsFromJSON();
            reader.countLogDataFileLines();
        } catch (final InvalidAnimalBirthDateException e) {
            throw new RuntimeException("При вызове метода произошла ошибка\n" + e);
        }
    }

}
