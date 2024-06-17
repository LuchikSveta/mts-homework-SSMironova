package ru.mts.siebel.springmvc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    //Задание 2: Обработка POST-запросов
    @PostMapping
    public String greet(@RequestParam final String name) {
        return "Hello, " + name + "!";
    }

}
