package ru.mts.siebel.api.service;

import ru.mts.siebel.model.AbstractAnimal;

public interface ISearchService {

    void checkLeapYearAnimal(final AbstractAnimal animal) throws Exception;

}
