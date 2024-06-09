package ru.mts.siebel.starter.api.model;

import java.time.LocalDate;

public interface IAnimal {

    String getType();

    String getName();

    String getBreed();

    Double getCost();

    String getCharacter();

    LocalDate getBirthDate();

    String getBirthDateFormat();

    String getSecretInformation();

    String getTypeAndName();

    int getAge();

}
