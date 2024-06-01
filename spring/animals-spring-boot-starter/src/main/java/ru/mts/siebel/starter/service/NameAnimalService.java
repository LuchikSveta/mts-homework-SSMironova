package ru.mts.siebel.starter.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class NameAnimalService {

    @Value("#{'${animal.cat.names}'.split(',')}")
    private List<String> cat;

    @Value("#{'${animal.dog.names}'.split(',')}")
    private List<String> dog;

    @Value("#{'${animal.mouse.names}'.split(',')}")
    private List<String> mouse;

    @Value("#{'${animal.shark.names}'.split(',')}")
    private List<String> shark;

    @Value("#{'${animal.wolf.names}'.split(',')}")
    private List<String> wolf;

}
