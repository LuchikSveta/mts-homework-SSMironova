package ru.mts.siebel.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.exception.EmptyAnimalsException;
import ru.mts.siebel.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

class AnimalsRepositoryImplTest {

    private final IAnimalsRepository repository = new AnimalsRepositoryImpl();

    @BeforeEach
    void setRepository() {
        repository.clear();
        repository.add(new Cat("Kruzik", "Cat", 1000, "Character", "2024-01-21"));
        repository.add(new Dog("Stich", "Dog", 1000, "Character", "2020-06-19"));
        repository.add(new Mouse("Mouse", "Mouse", 1000, "Character", "2017-10-25"));
        repository.add(new Shark("Shark", "Shark", 1000, "Character", "2012-03-06"));
        repository.add(new Wolf("Wolf", "Wolf", 1000, "Character", "2006-04-21"));
        repository.add(new Cat("Barsik", "Cat", 1000, "Character", "2016-01-21"));
        repository.add(new Cat("Marusya", "Cat", 1000, "Character", "2018-01-21"));
        repository.add(new Dog("Bulka", "Dog", 1000, "Character", "2023-06-19"));
    }

    @Test
    @DisplayName("Проверка метода findLeapYearNames на корректность")
    void findLeapYearNames() {
        Map<String, LocalDate> animals = repository.findLeapYearNames();
        System.out.println(animals);
        assertEquals(LocalDate.of(2024, 1, 21), animals.get("Cat Kruzik"));
        assertEquals(LocalDate.of(2020, 6, 19), animals.get("Dog Stich"));
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
        Map<String, Integer> animals = repository.findDuplicate();
        assertEquals(5, animals.size());
        assertEquals(3, animals.get("Cat"));
        assertEquals(2, animals.get("Dog"));
        assertEquals(1, animals.get("Mouse"));
        assertEquals(1, animals.get("Shark"));
        assertEquals(1, animals.get("Wolf"));
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

}