package ru.mts.siebel.starter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mts.siebel.starter.api.model.IAnimal;
import ru.mts.siebel.starter.api.service.IFileAnimalService;
import ru.mts.siebel.starter.serializer.OlderAnimalSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileAnimalService implements IFileAnimalService {

    @Autowired
    FilePathsService filePathsService;

    @Override
    public void createDirectory(final String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createFile(final String filePath) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearFile() {
        List<String> filePaths = new ArrayList<>(List.of(filePathsService.getSecretStoreFile(), filePathsService.getLogDataFile(), filePathsService.getResultsFile()));
        for (String filePath : filePaths) {
            Path path = Paths.get(filePath);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void generateSecretInformation() {
        createDirectory(filePathsService.getSecretStore());
        createFile(filePathsService.getSecretStoreFile());

        Path path = Paths.get(filePathsService.getSecretStoreFile());
        int n = 1 + (int) Math.round(Math.random() * 20);
        try {
            for (int i = 1; i < n; i++) {
                Files.writeString(path, "SECRET_INFORMATION_" + i + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSecretInformation() {
        Path path = Paths.get(filePathsService.getSecretStoreFile());
        try {
            List<String> file = Files.readAllLines(path);
            int n = (int) (Math.random() * file.size());
            return file.get(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logAnimals(final int n, final IAnimal animal) {
        createDirectory(filePathsService.getLogData());
        createFile(filePathsService.getLogDataFile());

        Path path = Paths.get(filePathsService.getLogDataFile());
        try {
            Files.writeString(path, n + ". " + animal.getTypeAndName() + ", " + animal.getCost() + ", " + animal.getBirthDateFormat() + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeAnimalsToJson(final Map<IAnimal, Integer> animals) {
        createDirectory(filePathsService.getResults());
        createFile(filePathsService.getResultsFile());

        List<OlderAnimalSerializer> animalSerializerList = new ArrayList<>();
        animals.forEach(
                (key, value) -> animalSerializerList.add(new OlderAnimalSerializer(key, value))
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        Path path = Paths.get(filePathsService.getResultsFile());
        try {
            mapper.writeValue(path.toFile(), animalSerializerList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
