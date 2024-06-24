package ru.mts.siebel.springmvc.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotBlank(message = "Поле Имя не может быть пустым")
    @Size(min = 2)
    private String name;

}
