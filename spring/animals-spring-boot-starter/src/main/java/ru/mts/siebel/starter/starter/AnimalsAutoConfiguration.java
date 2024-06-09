package ru.mts.siebel.starter.starter;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import ru.mts.siebel.starter.api.service.IFileAnimalService;
import ru.mts.siebel.starter.api.service.ISearchService;
import ru.mts.siebel.starter.serializer.OlderAnimalDeserializer;
import ru.mts.siebel.starter.service.*;

@SpringBootConfiguration
public class AnimalsAutoConfiguration {

    @Bean
    public FilePathsService filePathsService() {
        return new FilePathsService();
    }

    @Bean
    public NameAnimalService nameAnimalService() {
        return new NameAnimalService();
    }

    @Bean
    public IFileAnimalService fileAnimalService() {
        return new FileAnimalService();
    }

    @Bean
    public ISearchService searchService() {
        return new SearchServiceImpl();
    }

    @Bean
    public OlderAnimalDeserializer deserializer() {
        return new OlderAnimalDeserializer();
    }

    @Bean
    public ResultReader resultReader() {
        return new ResultReader();
    }

    @Bean
    @Scope("prototype")
    public CreateAnimalServiceImpl createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

}
