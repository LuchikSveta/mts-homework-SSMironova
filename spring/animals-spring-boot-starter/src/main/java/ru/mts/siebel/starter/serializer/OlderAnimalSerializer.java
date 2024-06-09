package ru.mts.siebel.starter.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.mts.siebel.starter.api.model.IAnimal;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

public class OlderAnimalSerializer implements IAnimal {

    private final IAnimal animal;
    private final int age;

    public OlderAnimalSerializer(final IAnimal animal, final int age) {
        this.animal = animal;
        this.age = age;
    }

    @Override
    public String getType() {
        return animal.getType();
    }

    @Override
    public String getName() {
        return animal.getName();
    }

    @Override
    public String getBreed() {
        return animal.getBreed();
    }

    @Override
    public Double getCost() {
        return animal.getCost();
    }

    @Override
    public String getCharacter() {
        return animal.getCharacter();
    }

    @Override
    @JsonFormat(pattern = "yyyy/MM/dd")
    public LocalDate getBirthDate() {
        return animal.getBirthDate();
    }

    @Override
    public String getBirthDateFormat() {
        return animal.getBirthDateFormat();
    }

    @Override
    public String getSecretInformation() {
        return Base64.getEncoder().encodeToString(animal.getSecretInformation().getBytes(StandardCharsets.UTF_8));
    }
    
    @Override
    public String getTypeAndName() {
        return animal.getTypeAndName();
    }

    @Override
    public int getAge() {
        return age;
    }

}
