package ru.mts.siebel.model;

import java.time.LocalDate;

public class Dog extends Pet {

    public Dog(final String name, final String breed, final double cost, final String character, final String birthDate) {

        super(name, breed, cost, character, birthDate);

    }

    public Dog(final String name, final String breed, final double cost, final String character, final LocalDate birthDate, final String secretInformation) {

        super(name, breed, cost, character, birthDate, secretInformation);

    }

}
