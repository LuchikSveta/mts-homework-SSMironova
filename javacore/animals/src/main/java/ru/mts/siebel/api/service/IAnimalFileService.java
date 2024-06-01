package ru.mts.siebel.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mts.siebel.api.model.IAnimal;
import ru.mts.siebel.serializer.OlderAnimalSerializer;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IAnimalFileService {

    private static void createResourcesDirectory() {
        Path path = Paths.get("C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources");
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createDirectory(final String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createFile(final String filePath) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void clearFile() {
        List<String> filePaths = new ArrayList<>(List.of(
                "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\secretStore\\secretInformation.txt",
                "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\animals\\logData.txt",
                "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\results\\findOlderAnimals.json"
        ));
        for (String filePath : filePaths) {
            Path path = Paths.get(filePath);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void generateSecretInformation() {
        String directoryPath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\secretStore";
        String filePath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\secretStore\\secretInformation.txt";

        createResourcesDirectory();
        createDirectory(directoryPath);
        createFile(filePath);

        Path path = Paths.get(filePath);
        int n = 1 + (int) Math.round(Math.random() * 20);
        try {
            for (int i = 1; i < n; i++) {
                Files.writeString(path, "SECRET_INFORMATION_" + i + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void logAnimals(final int n, final IAnimal animal) {
        String directoryPath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\animals";
        String filePath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\animals\\logData.txt";

        createResourcesDirectory();
        createDirectory(directoryPath);
        createFile(filePath);
        Path path = Paths.get(filePath);
        try {
            Files.writeString(path, n + ". " + animal.getTypeAndName() + ", " + animal.getCost() + ", " + animal.getBirthDateFormat() + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getSecretInformation() {
        String directoryPath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\secretStore";
        String filePath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\secretStore\\secretInformation.txt";

        createResourcesDirectory();
        createDirectory(directoryPath);
        createFile(filePath);

        Path path = Paths.get(filePath);
        try {
            List<String> file = Files.readAllLines(path);
            int n = (int) (Math.random() * file.size());
            return file.get(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void writeAnimalsToJson(final Map<IAnimal, Integer> animals) {
        String directoryPath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\results";
        String filePath = "C:\\Users\\svetl\\IdeaProjects\\mts-homework-SSMironova\\javacore\\animals\\src\\main\\resources\\results\\findOlderAnimals.json";

        createResourcesDirectory();
        createDirectory(directoryPath);
        createFile(filePath);

        List<OlderAnimalSerializer> animalSerializerList = new ArrayList<>();
        animals.forEach(
                (key, value) -> animalSerializerList.add(new OlderAnimalSerializer(key, value))
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        Path path = Paths.get(filePath);
        try {
            mapper.writeValue(path.toFile(), animalSerializerList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
