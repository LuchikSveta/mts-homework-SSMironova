package ru.mts.siebel.repository;

import ru.mts.siebel.api.repository.IAnimal;
import ru.mts.siebel.api.repository.IAnimalsRepository;
import ru.mts.siebel.exception.EmptyAnimalsException;
import ru.mts.siebel.exception.InvalidAverageAgeException;
import ru.mts.siebel.exception.InvalidAverageCostException;
import ru.mts.siebel.exception.InvalidMaxAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

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
        return animals.stream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.getClass().getSimpleName() + " " + animal.getName(), IAnimal::getBirthDate));
    }

    @Override
    public Map<IAnimal, Integer> findOlderAnimal(final int n) {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        Map<IAnimal, Integer> animalsMaxAge = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() >=
                        animals.stream()
                                .mapToInt(a -> Period.between(a.getBirthDate(), LocalDate.now()).getYears())
                                .max()
                                .orElseThrow(InvalidMaxAgeException::new))
                .collect(Collectors.toMap(animal -> animal, animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));

        Map<IAnimal, Integer> animalsOlderN = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > n)
                .collect(Collectors.toMap(animal -> animal, animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));
        if (animalsOlderN.size() == 0) {
            return animalsMaxAge;
        }
        return animalsOlderN;
    }

    @Override
    public Map<String, List<IAnimal>> findDuplicate() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        return animals.stream()
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName()));
    }

    @Override
    public Double findAverageAge() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        return animals.stream()
                .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElseThrow(InvalidAverageAgeException::new);
    }

    @Override
    public List<IAnimal> findOldAndExpensive() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        return animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost() >
                        animals.stream()
                                .mapToDouble(IAnimal::getCost)
                                .average()
                                .orElseThrow(InvalidAverageCostException::new))
                .sorted(Comparator.comparing(IAnimal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinConstAnimals() {
        if (animals.size() == 0) {
            throw new EmptyAnimalsException();
        }
        return animals.stream()
                .sorted(Comparator.comparing(IAnimal::getCost))
                .limit(3)
                .sorted((o1, o2) -> -o1.getName().compareTo(o2.getName()))
                .map(IAnimal::getName)
                .collect(Collectors.toList());
    }

}
