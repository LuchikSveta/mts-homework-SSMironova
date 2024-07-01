package ru.mts.siebel.springmvc.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.siebel.springmvc.dao.Person;

@RestController
@RequestMapping("/greet")
public class GreetController {

    //Задание 2: Обработка POST-запросов
    @PostMapping
    public String greet(@RequestBody final Person person) {
        return "Hello, " + person.getName() + "!";
    }

}
