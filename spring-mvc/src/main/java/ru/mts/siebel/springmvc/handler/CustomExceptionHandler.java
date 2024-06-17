package ru.mts.siebel.springmvc.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mts.siebel.springmvc.exception.CustomException;
import ru.mts.siebel.springmvc.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(final UserNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    public String handleUserNotFoundException(final CustomException exception) {
        return exception.getMessage();
    }

}
