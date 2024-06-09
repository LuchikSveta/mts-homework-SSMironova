package ru.mts.siebel.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mts.siebel.api.model.IAnimal;
import ru.mts.siebel.model.*;
import ru.mts.siebel.serializer.OlderAnimalDeserializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ResultReader {

    public void readAnimalsFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<OlderAnimalDeserializer> animalDeserializerList;

        Path path = Paths.get("C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\results\\findOlderAnimals.json");
        try {
            animalDeserializerList = mapper.readValue(path.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<IAnimal> animals = new ArrayList<>();
        if (animalDeserializerList.size() != 0) {
            for (OlderAnimalDeserializer animal : animalDeserializerList) {
                animals.add(createAnimal(animal));
            }
        }
        System.out.println("\nЖивотные, прочитанные из файла findOlderAnimals.json:\n" + animals);
    }

    public IAnimal createAnimal(final OlderAnimalDeserializer animal) {
        switch (animal.getType()) {
            case "Cat":
                return new Cat(animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDate(), animal.getSecretInformation());
            case "Dog":
                return new Dog(animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDate(), animal.getSecretInformation());
            case "Mouse":
                return new Mouse(animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDate(), animal.getSecretInformation());
            case "Shark":
                return new Shark(animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDate(), animal.getSecretInformation());
            case "Wolf":
                return new Wolf(animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDate(), animal.getSecretInformation());
            default:
                return null;
        }
    }

    public void countLogDataFileLines() {
        Path path = Paths.get("C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\animals\\logData.txt");
        try {
            System.out.println("\nКоличество строк в файле logData = " + Files.readAllLines(path).size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
