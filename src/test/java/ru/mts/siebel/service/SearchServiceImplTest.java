package ru.mts.siebel.service;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import ru.mts.siebel.api.service.ISearchService;
import ru.mts.siebel.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.exception.InvalidAnimalException;
import ru.mts.siebel.model.*;
import ru.mts.siebel.util.RandomDateUtil;

class SearchServiceImplTest {

    private final ISearchService service = new SearchServiceImpl();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Проверка на високосный год")
    void checkLeapYearAnimalKitty() throws Exception {
        AbstractAnimal animal = new Cat("Kitty", "Cat", 1000, "Nice", "2020-05-12");
        service.checkLeapYearAnimal(animal);
        assertEquals("KITTY был рожден в високосный год\r\n", outContent.toString());
    }

    @Test
    @DisplayName("Проверка на невисокосный год")
    void checkLeapYearAnimalWhiteFang() throws Exception {
        AbstractAnimal animal = new Wolf("White Fang", "Wolf", 1000, "Ferocious", "2019-07-12");
        service.checkLeapYearAnimal(animal);
        assertEquals("WHITE FANG не был рожден в високосный год\r\n", outContent.toString());
    }

    @ParameterizedTest
    @DisplayName("Проверка на високосный год с параметрами")
    @MethodSource("fetchDataLeapYear")
    void checkLeapYearAnimal(final AbstractAnimal animal) throws Exception {
        service.checkLeapYearAnimal(animal);
        assertEquals(animal.getName().toUpperCase() + " был рожден в високосный год\r\n", outContent.toString());
    }

    @ParameterizedTest
    @DisplayName("Проверка на невисокосный год с параметрами")
    @ValueSource(ints = {2014, 2018, 2019, 2021, 2022, 2023})
    void checkNotLeapYearAnimal(Integer year) throws Exception {
        AbstractAnimal animal = new Wolf("White Fang", "Wolf", 1000, "Ferocious", "2019-07-12");
        animal.setBirthDate(LocalDate.of(year, 1 + (int)(Math.random() * 11), 1 + (int)(Math.random() * 28)));
        service.checkLeapYearAnimal(animal);
        assertEquals("WHITE FANG не был рожден в високосный год\r\n", outContent.toString());
    }

    @ParameterizedTest
    @DisplayName("Выброс ошибки из-за некоррекого животного")
    @NullSource
    void checkLeapYearNullAnimalWithException(final AbstractAnimal animal) {
        RuntimeException runtimeException = assertThrows(InvalidAnimalException.class, () -> service.checkLeapYearAnimal(animal));
        assertEquals("На вход пришел некорректный объект животного " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), runtimeException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Проверка на корректность даты рождения")
    @MethodSource("fetchData")
    void checkLeapYearAnimalWithDateException(final AbstractAnimal animal) {
        animal.setBirthDate(null);
        Exception exception = assertThrows(InvalidAnimalBirthDateException.class, () -> service.checkLeapYearAnimal(animal));
        assertEquals("У животного " + animal.getClass().getSimpleName().toUpperCase() + " не указана дата его рождения", exception.getMessage());
    }

    private static Stream<Arguments> fetchData() {
        return Stream.of(
                Arguments.arguments(new Cat("Cat", "Cat", 1000, "Character", RandomDateUtil.createRandomDate(2010, 2024).toString())),
                Arguments.arguments(new Dog("Dog", "Dog", 1000, "Character", RandomDateUtil.createRandomDate(2010, 2024).toString())),
                Arguments.arguments(new Mouse("Mouse", "Mouse", 1000, "Character", RandomDateUtil.createRandomDate(2010, 2024).toString())),
                Arguments.arguments(new Shark("Shark", "Shark", 1000, "Character", RandomDateUtil.createRandomDate(2010, 2024).toString())),
                Arguments.arguments(new Wolf("Wolf", "Wolf", 1000, "Character", RandomDateUtil.createRandomDate(2010, 2024).toString()))
        );
    }

    private static Stream<Arguments> fetchDataLeapYear() {
        return Stream.of(
                Arguments.arguments(new Cat("Cat", "Cat", 1000, "Character", "2024-01-21")),
                Arguments.arguments(new Dog("Dog", "Dog", 1000, "Character", "2020-06-19")),
                Arguments.arguments(new Mouse("Mouse", "Mouse", 1000, "Character", "2016-10-25")),
                Arguments.arguments(new Shark("Shark", "Shark", 1000, "Character", "2012-03-06")),
                Arguments.arguments(new Wolf("Wolf", "Wolf", 1000, "Character", "2008-04-21"))
        );
    }

}