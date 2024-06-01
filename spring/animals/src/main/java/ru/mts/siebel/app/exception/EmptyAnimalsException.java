package ru.mts.siebel.app.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmptyAnimalsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "На вход пришел пустой список животных";

    public EmptyAnimalsException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
