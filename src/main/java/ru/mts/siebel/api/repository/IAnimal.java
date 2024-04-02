package ru.mts.siebel.api.repository;

import java.time.LocalDate;

public interface IAnimal {

    String getBreed();

    String getName();

    Double getCost();

    String getCharacter();

    LocalDate getBirthDate();

    String getBirthDateFormat();

}
