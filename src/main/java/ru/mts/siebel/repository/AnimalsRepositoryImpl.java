package ru.mts.siebel.repository;

import ru.mts.siebel.api.repository.IAnimal;
import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.exception.EmptyAnimalsException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalsRepositoryImpl implements IAnimalsRepository {

    private final List<IAnimal> animals = new ArrayList<>();

    @Override
    public void add(final IAnimal animal) {
        animals.add(animal);
    }

    @Override
    public void clear() {
        animals.clear();
    }

    @Override
    public void setAnimalsFromMap(final Map<String, List<IAnimal>> animalsMap) {
        for (String key : animalsMap.keySet()) {
            animals.addAll(animalsMap.get(key));
        }
    }

    @Override
    public List<IAnimal> get() {
        return animals;
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        Map<String, LocalDate> animalsLeapYear = new HashMap<>();
        for (final IAnimal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                animalsLeapYear.put(animal.getClass().getSimpleName() + " " + animal.getName(), animal.getBirthDate());
            }
        }
        return animalsLeapYear;
    }

    @Override
    public Map<IAnimal, Integer> findOlderAnimal(final int n) {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        int maxAge = 0;
        Map<IAnimal, Integer> animalsMaxAge = new HashMap<>();
        Map<IAnimal, Integer> animalsOlderN = new HashMap<>();
        for (final IAnimal animal : animals) {
            int age = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
            if (age > n) {
                animalsOlderN.put(animal, age);
            }
            if (age >= maxAge) {
                maxAge = age;
                animalsMaxAge.put(animal, age);
            }
        }
        if (animalsOlderN.size() == 0) {
            return animalsMaxAge;
        }
        return animalsOlderN;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        Map<String, Integer> animalsNumber = new HashMap<>();
        for (final IAnimal animal : animals) {
            String animalType = animal.getClass().getSimpleName();
            if (animalsNumber.size() == 0 || animalsNumber.get(animalType) == null) {
                animalsNumber.put(animalType, 1);
            } else {
                animalsNumber.put(animalType, animalsNumber.get(animalType) + 1);
            }
        }
        return animalsNumber;
    }

}
