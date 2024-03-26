package ru.mts.siebel.api.service;

import ru.mts.siebel.model.*;
import ru.mts.siebel.util.RandomDateUtil;

public interface ICreateAnimalService {

    default void createAnimal() {
        int number = 0;
        while (number++ < 10) {
            AbstractAnimal animal = getAnimal(number, "Animal" + number, 1000, "Character" + number);
            System.out.println(animal);
        }
    }
    
    default AbstractAnimal getAnimal(int num, String name, double cost, String character) {
        int n = 1 + (int)(Math.random() * 5);
        String birthDate = RandomDateUtil.createRandomDate(2010, 2024).toString();
        cost *= (num + n);
        switch (n) {
            case 1:
                return new Cat(name, "Cat" + num, cost, character, birthDate);
            case 2:
                return new Dog(name, "Dog" + num, cost, character, birthDate);
            case 3:
                return new Mouse(name, "Mouse" + num, cost, character, birthDate);
            case 4:
                return new Shark(name, "Shark" + num, cost, character, birthDate);
            case 5:
                return new Wolf(name, "Wolf" + num, cost, character, birthDate);
            default:
                return null;
        }
    }

}
