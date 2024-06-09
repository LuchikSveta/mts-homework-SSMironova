package ru.mts.siebel.app.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvalidAverageCostException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Не удалось вычислить среднюю стоимость всех животных";

    public InvalidAverageCostException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
