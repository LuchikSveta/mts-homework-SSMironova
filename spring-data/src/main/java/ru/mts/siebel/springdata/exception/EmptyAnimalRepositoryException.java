package ru.mts.siebel.springdata.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmptyAnimalRepositoryException extends RuntimeException {

    private static final String ERROR_MESSAGE = "В репозитории нет животных";

    public EmptyAnimalRepositoryException() {
        super(ERROR_MESSAGE + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
