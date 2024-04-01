package ru.mts.siebel.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAnimalsRepository {

    void add(IAnimal animal);

    void clear();

    void setAnimalsFromMap(Map<String, List<IAnimal>> animalsMap);

    List<IAnimal> get();

    Map<String, LocalDate> findLeapYearNames();

    Map<IAnimal, Integer> findOlderAnimal(int n);

    Map<String, Integer> findDuplicate();

}
