package ru.mts.siebel.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Base64;

public class OlderAnimalDeserializer {

    private String type;
    private String name; // имя
    private String breed; // порода
    private Double cost; // цена в магазине
    private String character; // характер
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate; // день рождения
    private String secretInformation; //секретная информация
    private int age;

    public OlderAnimalDeserializer() {
    }

    public OlderAnimalDeserializer(final String type, final String name, final String breed, final Double cost, final String character, final LocalDate birthDate, final String secretInformation, final int age) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = secretInformation;
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Double getCost() {
        return cost;
    }

    public String getCharacter() {
        return character;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSecretInformation() {
        return new String(Base64.getDecoder().decode(secretInformation));
    }

    public int getAge() {
        return age;
    }

}
