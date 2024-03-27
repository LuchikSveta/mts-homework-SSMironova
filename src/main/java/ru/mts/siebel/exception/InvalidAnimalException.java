package ru.mts.siebel.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvalidAnimalException extends RuntimeException {

    private static final String ERROR_MESSAGE = "На вход пришел некорректный объект животного";

    public InvalidAnimalException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
