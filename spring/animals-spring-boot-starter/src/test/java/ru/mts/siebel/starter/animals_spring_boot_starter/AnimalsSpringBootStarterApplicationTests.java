package ru.mts.siebel.starter.animals_spring_boot_starter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import ru.mts.siebel.starter.api.model.IAnimal;
import ru.mts.siebel.starter.api.service.ICreateAnimalService;
import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.starter.service.FilePathsService;
import ru.mts.siebel.starter.service.NameAnimalService;
import ru.mts.siebel.starter.starter.AnimalsAutoConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {AnimalsAutoConfiguration.class, AnimalsTestAutoConfiguration.class})
@ActiveProfiles("test")
class AnimalsSpringBootStarterApplicationTests {

	@Autowired
	@Qualifier("filePathsTestService")
	FilePathsService filePathsService;
	@Autowired
	@Qualifier("nameAnimalTestService")
	NameAnimalService nameAnimalService;
	@Autowired
	ICreateAnimalService createAnimalService;
	@Autowired
	ApplicationContext ctx;

	@Test
	@DisplayName("Проверка на корректное количество животных")
	void checkAnimalsNumber() throws InvalidAnimalBirthDateException {
		List<IAnimal> animals = setAnimalsFromMap(createAnimalService.createAnimal());
		assertEquals(10, animals.size());

		animals.clear();
		animals = setAnimalsFromMap(createAnimalService.createAnimal(15));
		assertEquals(15, animals.size());
	}

	@Test
	@DisplayName("Проверка имен из application-test")
	public void checkNamesServiceTest() {
		assertEquals(2, nameAnimalService.getWolf().size());
		assertEquals("Gaechka", nameAnimalService.getMouse().get(0));
		assertEquals("Ursula", nameAnimalService.getShark().get(1));
		assertTrue(nameAnimalService.getCat().contains("Fufik"));
		assertFalse(nameAnimalService.getDog().contains("Persic"));
	}

	@Test
	@DisplayName("Проверка файла secretTest из application-test")
	public void checkSecretFilePathsTestServiceTest() {
		Path path = Paths.get(filePathsService.getSecretStore() + "\\secretTest.txt");
		try {
			List<String> file = Files.readAllLines(path);
			assertEquals(1, file.size());
			assertEquals("secretTest in test", file.get(0));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	@DisplayName("Проверка наличия бина filePathsTestService из тестового config")
	public void existsFilePathsTestServiceBeanServiceTest() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		boolean exists = false;
		for (String beanName : beanNames) {
			if (beanName.equals("filePathsTestService")) {
				exists = true;
				break;
			}
		}
		assertTrue(exists);
	}

	@Test
	@DisplayName("Проверка наличия бина nameAnimalTestService из тестового config")
	public void existsNameAnimalTestServiceBeanServiceTest() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		boolean exists = false;
		for (String beanName : beanNames) {
			if (beanName.equals("nameAnimalTestService")) {
				exists = true;
				break;
			}
		}
		assertTrue(exists);
	}

	private List<IAnimal> setAnimalsFromMap(final Map<String, List<IAnimal>> animalsMap) {
		List<IAnimal> animals = new ArrayList<>();
		for (String key : animalsMap.keySet()) {
			animals.addAll(animalsMap.get(key));
		}
		return animals;
	}

}
