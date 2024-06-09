package ru.mts.siebel.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.mts.siebel.app.repository.AnimalsRepositoryImpl;
import ru.mts.siebel.starter.service.ResultReader;
import ru.mts.siebel.starter.api.model.IAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AnimalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> runCommands(ctx);
    }

    private static void runCommands(ApplicationContext ctx) {
        AnimalsRepositoryImpl animalsRepository = ctx.getBean(AnimalsRepositoryImpl.class);
        System.out.println("\nВсе животные:\n" + animalsRepository.get());

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

        ResultReader reader = ctx.getBean(ResultReader.class);
        reader.readAnimalsFromJSON();
        reader.countLogDataFileLines();
    }

}
