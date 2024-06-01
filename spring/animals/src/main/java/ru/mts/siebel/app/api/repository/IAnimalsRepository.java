package ru.mts.siebel.app.api.repository;

import ru.mts.siebel.starter.api.model.IAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAnimalsRepository {

    void add(IAnimal animal);

    int size();

    void clear();

    void setAnimalsFromMap(Map<String, List<IAnimal>> animalsMap);

    List<IAnimal> get();

    Map<String, LocalDate> findLeapYearNames();

    Map<IAnimal, Integer> findOlderAnimal(int n);

    Map<String, List<IAnimal>> findDuplicate();

    Double findAverageAge();

    List<IAnimal> findOldAndExpensive();

    List<String> findMinCostAnimals();

}
