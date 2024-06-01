package ru.mts.siebel.starter.model;

import java.time.LocalDate;

public class Mouse extends  Pet {

    public Mouse(final String name, final String breed, final double cost, final String character, final String birthDate) {

        super(name, breed, cost, character, birthDate);

    }

    public Mouse(final String name, final String breed, final double cost, final String character, final String birthDate, final String secretInformation) {

        super(name, breed, cost, character, birthDate, secretInformation);

    }

    public Mouse(final String name, final String breed, final double cost, final String character, final LocalDate birthDate, final String secretInformation) {

        super(name, breed, cost, character, birthDate, secretInformation);

    }

}
