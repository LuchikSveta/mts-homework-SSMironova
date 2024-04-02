package ru.mts.siebel.model;

import ru.mts.siebel.api.repository.IAnimal;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class AbstractAnimal implements IAnimal {

    protected String name; // имя
    protected String breed; // порода
    protected Double cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения

    public AbstractAnimal(final String name, final String breed, final double cost, final String character, final String birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = LocalDate.parse(birthDate);
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

    @Override
    public String toString() {
        return getClassName() + ": " + name;
    }

    @Override
    public String getClassName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getClassAndName() {
        return getClassName() + " " + name;
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
