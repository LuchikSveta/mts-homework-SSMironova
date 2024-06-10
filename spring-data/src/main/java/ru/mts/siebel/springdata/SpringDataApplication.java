package ru.mts.siebel.springdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.mts.siebel.springdata.entity.Animal;
import ru.mts.siebel.springdata.service.AnimalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
		return args -> runCommands(ctx);
	}

	private static void runCommands(final ApplicationContext ctx) {
		AnimalService animalService = ctx.getBean(AnimalService.class);
		System.out.println("\nВсе животные:\n" + animalService.findAll());

		Map<String, LocalDate> animalsLeapYear = animalService.findLeapYearNames();
		if (animalsLeapYear.size() == 0) {
			System.out.println("\nВсе животные родились не в високосный год");
		} else {
			System.out.println("\nЖивотные, которые родились в високосный год:\n" + animalsLeapYear);
		}

		int n = 5;
		System.out.println("\nЖивотные, возраст которых старше " + n + " лет:\n" + animalService.findOlderAnimal(n));

		System.out.println("\nДубликаты животных:\n" + animalService.findDuplicate());

		System.out.println("\nCредний возраст всех животных: " + animalService.findAverageAge());

		List<Animal> animalsOldAndExpensive = animalService.findOldAndExpensive();
		if (animalsOldAndExpensive.size() == 0) {
			System.out.println("\nНет таких животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных");
		} else {
			System.out.println("\nЖивотные, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных:\n" + animalsOldAndExpensive);
		}

		System.out.println("\n3 животных с самой низкой ценой: \n" + animalService.findMinCostAnimals());
	}

}
