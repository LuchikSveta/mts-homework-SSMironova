package ru.mts.siebel.model;

import ru.mts.siebel.api.repository.IAnimal;

public abstract class AbstractAnimal implements IAnimal {

    protected String name; // имя
    protected String breed; // порода
    protected Double cost; // цена в магазине
    protected String character; // характер

    public AbstractAnimal(String name, String breed, double cost, String character) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
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
    public String toString() {
        return getClass().getSimpleName() + ": " + this.getName();
    }

}
