package ru.mts.siebel.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.siebel.springdata.entity.Animal;
import ru.mts.siebel.springdata.exception.EmptyAnimalRepositoryException;
import ru.mts.siebel.springdata.exception.InvalidAverageAgeException;
import ru.mts.siebel.springdata.exception.InvalidAverageCostException;
import ru.mts.siebel.springdata.exception.InvalidMaxAgeException;
import ru.mts.siebel.springdata.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public List<Animal> findAll() {
        return repository.findAll();
    }

    public List<Animal> getAnimalsByName(final String name) {
        return repository.getAnimalsByName(name);
    }

    public int size() {
        return findAll().size();
    }

    public Animal addAnimal(final Animal animal) {
        return repository.save(animal);
    }

    public void deleteAnimalById(final Long id) {
        repository.deleteById(id);
    }

    public Map<String, LocalDate> findLeapYearNames() {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        return findAll().stream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(Animal::getTypeAndName, Animal::getBirthDate));
    }

    public Map<Animal, Integer> findOlderAnimal(final int n) {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        Map<Animal, Integer> animalsOlderN = findAll().stream()
                .filter(animal -> animal.getAge() > n)
                .collect(Collectors.toMap(animal -> animal, Animal::getAge));
        if (animalsOlderN.size() == 0) {
            int maxAge = findAll().stream()
                    .mapToInt(Animal::getAge)
                    .max()
                    .orElseThrow(InvalidMaxAgeException::new);
            return findAll().stream()
                    .filter(animal -> animal.getAge() >= maxAge)
                    .collect(Collectors.toMap(animal -> animal, Animal::getAge));
        }
        return animalsOlderN;
    }

    public Map<String, List<Animal>> findDuplicate() {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Animal::getTypeName));
    }

    public double findAverageAge() {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        return findAll().stream()
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow(InvalidAverageAgeException::new);
    }

    public List<Animal> findOldAndExpensive() {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        double averageCost = findAll().stream()
                .mapToDouble(Animal::getCost)
                .average()
                .orElseThrow(InvalidAverageCostException::new);
        return findAll().stream()
                .filter(animal -> animal.getAge() > 5)
                .filter(animal -> animal.getCost() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    public List<String> findMinCostAnimals() {
        if (size() == 0) {
            throw new EmptyAnimalRepositoryException();
        }
        return findAll().stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted((o1, o2) -> -o1.getName().compareTo(o2.getName()))
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

}
