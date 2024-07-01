package ru.mts.siebel.springmvc.dao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotBlank
    private int id;

    @NotBlank(message = "Поле Имя не может быть пустым")
    @Size(min = 2)
    private String name;

    @NotBlank(message = "Поле email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

}
