package ru.mts.siebel.model;

import ru.mts.siebel.api.repository.IAnimal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractAnimal implements IAnimal {

    protected String name; // имя
    protected String breed; // порода
    protected Double cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения животного в формате dd.MM.yyyy

    public AbstractAnimal(String name, String breed, double cost, String character, String birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = LocalDate.parse(birthDate);
        //this.birthDate = LocalDate.parse(LocalDate.parse(birthDate).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + this.getName();
    }

}
