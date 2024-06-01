package ru.mts.siebel.starter.api.service;

import ru.mts.siebel.starter.api.model.IAnimal;

import java.util.Map;

public interface IFileAnimalService {

    void clearFile();

    void createDirectory(String directoryPath);

    void createFile(String filePath);

    void generateSecretInformation();

    String getSecretInformation();

    void logAnimals(int n, IAnimal animal);

    void writeAnimalsToJson(Map<IAnimal, Integer> animals);

}
