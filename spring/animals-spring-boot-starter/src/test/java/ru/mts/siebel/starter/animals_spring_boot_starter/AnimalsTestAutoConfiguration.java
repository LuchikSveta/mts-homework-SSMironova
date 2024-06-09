package ru.mts.siebel.starter.animals_spring_boot_starter;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mts.siebel.starter.service.FilePathsService;
import ru.mts.siebel.starter.service.NameAnimalService;

@TestConfiguration
public class AnimalsTestAutoConfiguration {

    @Bean
    @Primary
    public NameAnimalService nameAnimalTestService() {
        return new NameAnimalService();
    }

    @Bean
    public FilePathsService filePathsTestService() {
        return new FilePathsService();
    }

}
