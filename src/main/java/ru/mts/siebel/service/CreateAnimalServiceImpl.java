package ru.mts.siebel.service;

import ru.mts.siebel.api.service.ICreateAnimalService;
import ru.mts.siebel.model.AbstractAnimal;

public class CreateAnimalServiceImpl implements ICreateAnimalService {

    public void createAnimalFromParent() throws Exception {
        ICreateAnimalService.super.createAnimal();
    }

    @Override
    public void createAnimal() {
        int number = 1;
        do {
            AbstractAnimal animal = getAnimal(number, "Animal" + number, 1000 * number, "Character" + number);
            System.out.println(animal);
        } while (number++ < 10);
    }

    public void createAnimal(final int number) {
        for (int i = 1; i < number + 1; i++) {
            AbstractAnimal animal = getAnimal(i, "Animal" + i, 1000 * i, "Character" + i);
            System.out.println(animal);
        }
    }

    @Override
    public AbstractAnimal getAnimal(int n, String name, double cost, String character) {
        return ICreateAnimalService.super.getAnimal(n, name, cost, character);
    }

}
