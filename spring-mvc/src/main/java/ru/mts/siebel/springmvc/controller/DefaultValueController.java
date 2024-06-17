package ru.mts.siebel.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultValueController {

    //Задание 10: Использование @RequestParam с параметрами по умолчанию
    @GetMapping("/default")
    @ResponseBody
    public String getDefault(@RequestParam(defaultValue = "Guest") final String name) {
        return "Hello, " + name + "!";
    }

}
