package ru.mts.siebel.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAnimalsRepository {

    void add(IAnimal animal);

    void clear();

    void setAnimalsMap (Map<String, List<IAnimal>> animalsMap);

    void setAnimalsListFromMap(Map<String, List<IAnimal>> animalsMap);

    List<IAnimal> getList();

    Map<String, List<IAnimal>> getMap();

    Map<String, LocalDate> findLeapYearNames();

    Map<IAnimal, Integer> findOlderAnimal(int n);

    Map<String, Integer> findDuplicate();

}
