package ru.mts.siebel.springdata;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.mts.siebel.springdata.entity.Animal;
import ru.mts.siebel.springdata.entity.AnimalType;
import ru.mts.siebel.springdata.repository.AnimalRepository;
import ru.mts.siebel.springdata.repository.AnimalTypeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataApplicationTests {

	@Autowired
	AnimalRepository animalRepository;
	@Autowired
	AnimalTypeRepository animalTypeRepository;

	@Test
	@Order(1)
	void testByContainers() {
		List<Animal> animals = animalRepository.findAll();
		assertEquals(10, animals.size());
		assertEquals(1, animals.get(0).getAge());
		assertEquals("Sweety", animals.get(5).getName());

		List<AnimalType> animalTypes = animalTypeRepository.findAll();
		assertEquals(5, animalTypes.size());
		assertEquals("Dog", animalTypes.get(1).getType());
	}

	@Test
	@Order(2)
	@Sql("/test.sql")
	void manualUpload() {
		List<Animal> animals = animalRepository.findAll();
		assertEquals(11, animalRepository.findAll().size());
		assertEquals("Snake", animals.get(10).getTypeName());

		List<AnimalType> animalTypes = animalTypeRepository.findAll();
		assertEquals(6, animalTypes.size());
		assertEquals("Snake", animalTypes.get(5).getType());
	}
}
