package ru.mts.siebel.model;

import ru.mts.siebel.api.model.IAnimal;
import ru.mts.siebel.api.service.IAnimalFileService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public abstract class AbstractAnimal implements IAnimal {

    protected String type;
    protected String name; // имя
    protected String breed; // порода
    protected Double cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения
    protected String secretInformation; //секретная информация

    public AbstractAnimal(final String name, final String breed, final double cost, final String character, final String birthDate) {
        this.type = getClass().getSimpleName();
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = LocalDate.parse(birthDate);
        setSecretInformation(IAnimalFileService.getSecretInformation());
    }

    public AbstractAnimal(final String name, final String breed, final Double cost, final String character, final LocalDate birthDate, final String secretInformation) {
        this.type = getClass().getSimpleName();
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
        setSecretInformation(secretInformation);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String getSecretInformation() {
        return new String(Base64.getDecoder().decode(secretInformation));
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setBreed(final String breed) {
        this.breed = breed;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }

    public void setCharacter(final String character) {
        this.character = character;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSecretInformation(final String secretInformation) {
        this.secretInformation = Base64.getEncoder().encodeToString(secretInformation.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return type + ": " + name;
    }

    public String getTypeAndName() {
        return type + " " + name;
    }

    @Override
    public String getBirthDateFormat() {
        return birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
