package ru.mts.siebel.springdata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;

@Data
@Entity
@NoArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // имя
    private String breed; // порода
    private Double cost; // цена в магазине
    private String character; // характер
    @Column(name = "birth_date")
    private LocalDate birthDate; // день рождения
    @Column(name = "secret_information")
    private String secretInformation; //секретная информация
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private AnimalType type;

    public Animal(String name, String breed, Double cost, String character, String birthDate, String secretInformation, AnimalType type) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = LocalDate.parse(birthDate);
        this.type = type;
        setSecretInformation(secretInformation);
    }

    public String getSecretInformation() {
        return new String(Base64.getDecoder().decode(secretInformation));
    }

    public void setSecretInformation(final String secretInformation) {
        this.secretInformation = Base64.getEncoder().encodeToString(secretInformation.getBytes(StandardCharsets.UTF_8));
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getTypeName() {
        return type.getType();
    }
    public String getTypeAndName() {
        return getTypeName() + " " + name;
    }

    @Override
    public String toString() {
        return getTypeAndName();
    }

}
