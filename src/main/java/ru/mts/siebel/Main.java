package ru.mts.siebel;

import ru.mts.siebel.service.CreateAnimalServiceImpl;

public class Main {

    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        System.out.println("Цикл while");
        try {
            createAnimalService.createAnimalFromParent();
        } catch (final Exception e) {
            throw new RuntimeException("При вызове метода произошла ошибка\n" + e);
        }
        System.out.println("\nЦикл do while");
        createAnimalService.createAnimal();
        System.out.println("\nЦикл for");
        createAnimalService.createAnimal(6);
    }

}
