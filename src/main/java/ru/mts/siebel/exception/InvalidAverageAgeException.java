package ru.mts.siebel.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvalidAverageAgeException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Не удалось вычислить средний возраст всех животных";

    public InvalidAverageAgeException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
