package ru.mts.siebel.app.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ru.mts.siebel.app.api.repository.IAnimalsRepository;
import ru.mts.siebel.app.exception.EmptyAnimalsException;
import ru.mts.siebel.starter.api.model.IAnimal;
import ru.mts.siebel.starter.api.service.IFileAnimalService;
import ru.mts.siebel.starter.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
class AnimalsRepositoryImplTest {

    @Autowired
    IFileAnimalService fileAnimalService;
    @Autowired
    IAnimalsRepository repository;

    @BeforeEach
    void setRepository() {
        repository.clear();
        repository.add(new Cat("Kuzya", "Cat", 1000, "Character", "2024-01-21", fileAnimalService.getSecretInformation()));
        repository.add(new Dog("Stitch", "Dog", 3500, "Character", "2020-06-19", fileAnimalService.getSecretInformation()));
        repository.add(new Mouse("Mouse", "Mouse", 3000, "Character", "2017-10-25", fileAnimalService.getSecretInformation()));
        repository.add(new Shark("Shark", "Shark", 5500, "Character", "2012-03-06", fileAnimalService.getSecretInformation()));
        repository.add(new Wolf("Wolf", "Wolf", 4070, "Character", "2006-04-21", fileAnimalService.getSecretInformation()));
        repository.add(new Cat("Barsik", "Cat", 2300, "Character", "2016-01-21", fileAnimalService.getSecretInformation()));
        repository.add(new Cat("Marusya", "Cat", 2100, "Character", "2018-01-21", fileAnimalService.getSecretInformation()));
        repository.add(new Dog("Bulka", "Dog", 3000, "Character", "2023-06-19", fileAnimalService.getSecretInformation()));
    }

    void addMoreAnimals() {
        repository.add(new Cat("Marsik", "Cat", 1800, "Character", "2005-01-21", fileAnimalService.getSecretInformation()));
        repository.add(new Dog("Bublik", "Dog", 6050, "Character", "2013-06-19", fileAnimalService.getSecretInformation()));
        repository.add(new Cat("Sweety", "Mouse", 1050, "Character", "2017-10-25", fileAnimalService.getSecretInformation()));
        repository.add(new Shark("Cookie", "Shark", 5500, "Character", "2012-03-06", fileAnimalService.getSecretInformation()));
    }

    @Test
    @DisplayName("Проверка метода findLeapYearNames на корректность")
    void findLeapYearNames() {
        Map<String, LocalDate> animals = repository.findLeapYearNames();
        assertEquals(LocalDate.of(2024, 1, 21), animals.get("Cat Kuzya"));
        assertEquals(LocalDate.of(2020, 6, 19), animals.get("Dog Stitch"));
        assertEquals(LocalDate.of(2012, 3, 6), animals.get("Shark Shark"));
        assertEquals(LocalDate.of(2016, 1, 21), animals.get("Cat Barsik"));
        assertEquals(4, animals.size());
    }

    @Test
    @DisplayName("Проверка метода findOlderAnimal на корректность")
    void findOlderAnimal() {
        assertEquals(5, repository.findOlderAnimal(5).size());
        assertEquals(2, repository.findOlderAnimal(10).size());
        assertEquals(1, repository.findOlderAnimal(15).size());
    }

    @Test
    @DisplayName("Проверка метода findDuplicate на корректность")
    void findDuplicate() {
        Map<String, List<IAnimal>> animals = repository.findDuplicate();
        assertEquals(5, animals.size());
        assertEquals(3, animals.get("Cat").size());
        assertEquals(2, animals.get("Dog").size());
        assertEquals(1, animals.get("Mouse").size());
        assertEquals(1, animals.get("Shark").size());
        assertEquals(1, animals.get("Wolf").size());
    }

    @Test
    @DisplayName("Проверка метода findAverageAge на корректность")
    void findAverageAge() {
        assertEquals(6.625, repository.findAverageAge());
        assertNotEquals(6.5, repository.findAverageAge());
        addMoreAnimals();
        assertEquals(8.333333333333334, repository.findAverageAge());
        assertNotEquals(8.25, repository.findAverageAge());
    }

    @Test
    @DisplayName("Проверка метода findOldAndExpensive на корректность")
    void findOldAndExpensive() {
        assertEquals(2, repository.findOldAndExpensive().size());
        addMoreAnimals();
        assertEquals(4, repository.findOldAndExpensive().size());
    }

    @Test
    @DisplayName("Проверка метода findMinCostAnimals на корректность")
    void findMinConstAnimals() {
        List<String> animals = repository.findMinCostAnimals();
        assertEquals(3, animals.size());
        assertTrue(animals.contains("Marusya"));
        assertTrue(animals.contains("Kuzya"));
        assertTrue(animals.contains("Barsik"));
        addMoreAnimals();
        animals = repository.findMinCostAnimals();
        assertEquals(3, animals.size());
        assertTrue(animals.contains("Sweety"));
        assertTrue(animals.contains("Kuzya"));
        assertTrue(animals.contains("Marsik"));
        assertFalse(animals.contains("Marusya"));
        assertFalse(animals.contains("Barsik"));
    }

    @Test
    @DisplayName("Проверка метода findLeapYearNames. Выброс ошибки из-за отсутсвтвия животных")
    void findLeapYearNamesEmptyRep() {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, repository::findLeapYearNames);
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Проверка метода findOlderAnimal. Выброс ошибки из-за отсутсвтвия животных")
    @ValueSource(ints = {3, 4, 5})
    void findOlderAnimalEmptyRep(int n) {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, () -> repository.findOlderAnimal(n));
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @Test
    @DisplayName("Проверка метода findDuplicate. Выброс ошибки из-за отсутсвтвия животных")
    void findDuplicateEmptyRep() {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, repository::findDuplicate);
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @Test
    @DisplayName("Проверка метода findAverageAge. Выброс ошибки из-за отсутсвтвия животных")
    void findAverageAgeEmptyRep() {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, repository::findAverageAge);
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @Test
    @DisplayName("Проверка метода findOldAndExpensive. Выброс ошибки из-за отсутсвтвия животных")
    void findOldAndExpensiveEmptyRep() {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, repository::findOldAndExpensive);
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @Test
    @DisplayName("Проверка метода findMinCostAnimals. Выброс ошибки из-за отсутсвтвия животных")
    void findMinConstAnimalsEmptyRep() {
        repository.clear();
        RuntimeException runtimeException = assertThrows(EmptyAnimalsException.class, repository::findMinCostAnimals);
        assertEquals("На вход пришел пустой список животных " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

}
