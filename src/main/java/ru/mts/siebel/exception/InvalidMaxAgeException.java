package ru.mts.siebel.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvalidMaxAgeException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Не удалось вычислить максимальный возраст среди всех животных";

    public InvalidMaxAgeException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
