package ru.mts.siebel.model;

import java.time.LocalDate;

public class Predator extends AbstractAnimal {

    public Predator(final String name, final String breed, final double cost, final String character, final String birthDate) {

        super(name, breed, cost, character, birthDate);

    }

    public Predator(final String name, final String breed, final double cost, final String character, final LocalDate birthDate, final String secretInformation) {

        super(name, breed, cost, character, birthDate, secretInformation);

    }

}
