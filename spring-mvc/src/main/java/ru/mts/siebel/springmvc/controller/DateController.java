package ru.mts.siebel.springmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {

    //Задание 9: Использование @PathVariable с регулярными выражениями
    @GetMapping("/date/{date}")
    public ResponseEntity<String> getDate(@PathVariable final String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.badRequest().body("Invalid date format: " + date + ", need: yyyy-MM-dd");
        }
        return ResponseEntity.ok("Valid date: " + date);
    }

}
