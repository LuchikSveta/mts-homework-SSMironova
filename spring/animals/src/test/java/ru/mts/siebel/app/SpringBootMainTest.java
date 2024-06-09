package ru.mts.siebel.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.siebel.app.api.repository.IAnimalsRepository;
import ru.mts.siebel.starter.api.service.ICreateAnimalService;
import ru.mts.siebel.starter.exception.InvalidAnimalBirthDateException;
import ru.mts.siebel.starter.service.FilePathsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class SpringBootMainTest {

    @Autowired
    ICreateAnimalService createAnimalService;
    @Autowired
    FilePathsService filePathsService;
    @Autowired
    IAnimalsRepository repository;

    @BeforeEach
    void clear() {
        repository.clear();
    }

    @Test
    @DisplayName("Проверка на корректное количество животных")
    void checkAnimalsNumber() throws InvalidAnimalBirthDateException {
        repository.setAnimalsFromMap(createAnimalService.createAnimal());
        assertEquals(10, repository.get().size());

        repository.clear();
        repository.setAnimalsFromMap(createAnimalService.createAnimal(15));
        assertEquals(15, repository.size());
    }

    @Test
    @DisplayName("Проверка количества строк в файле")
    void checkLogFile() throws InvalidAnimalBirthDateException, IOException {
        repository.setAnimalsFromMap(createAnimalService.createAnimal());
        assertEquals(10, Files.readAllLines(Paths.get(filePathsService.getLogDataFile())).size());

        repository.setAnimalsFromMap(createAnimalService.createAnimal(15));
        assertEquals(15, Files.readAllLines(Paths.get(filePathsService.getLogDataFile())).size());
    }

}
