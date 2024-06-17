package ru.mts.siebel.springdata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "animal_types")
public class AnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String type;

    @Override
    public String toString() {
        return type;
    }

}
