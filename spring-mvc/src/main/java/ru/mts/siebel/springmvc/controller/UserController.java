package ru.mts.siebel.springmvc.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.mts.siebel.springmvc.dao.User;
import ru.mts.siebel.springmvc.exception.CustomException;
import ru.mts.siebel.springmvc.exception.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

    //Задание 3: Валидация данных
    @PostMapping
    public String userParams(@Valid @RequestBody final User user) {
        return String.format("User %s with email %s registered", user.getName(), user.getEmail());
    }

    //Задание 4: Использование @RequestParam и @PathVariable
    @GetMapping("/get/{name}")
    public String getUser(@PathVariable final String name, @RequestParam final int id) {
        return "Hello, " + name + "! Your id is " + id;
    }

    //Задание 5: Обработка исключений с помощью @ExceptionHandler
    @GetMapping("/{id}")
    public String getUserById(@PathVariable final int id) {
        if (id == 10) {
            throw new UserNotFoundException("User was not found");
        }
        return "User was found";
    }

    //Задание 6: Использование @RequestHeader
    @GetMapping("/agent")
    public String getUserAgent(@RequestHeader("User-Agent") final String userAgent) {
        return "User-Agent: " + userAgent;
    }

    //Задание 9: Работа с @ResponseStatus
    @GetMapping("/exception")
    public String getUserException() {
        throw new CustomException("CustomException with ResponseStatus = 404 Not Found");
    }

}
