package ru.mts.siebel.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    //Задание 1
    @GetMapping
    public String getHelloWorld() {
        return "Hello World";
    }

}
