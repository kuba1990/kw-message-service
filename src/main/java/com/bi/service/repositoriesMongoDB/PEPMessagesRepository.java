package com.bi.service.repositoriesMongoDB;

import com.bi.service.model.mongodb.PepPerson;

import java.util.List;

public interface PEPMessagesRepository {
    public List<PepPerson> findPersons(int numbersOfMessages);
}