package com.bi.service.repositoriesMongoDB;

import com.bi.service.config.DatabaseProperties;
import com.bi.service.model.mongodb.PepPerson;
import com.bi.service.repositoriesMariaDB.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PEPMessageRepositoryImpl implements PEPMessagesRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;
    private final DatabaseProperties config;
    private PersonRepository personRepository;


    public PEPMessageRepositoryImpl(MongoTemplate mongoTemplate, MongoOperations mongoOperations, DatabaseProperties config, PersonRepository personRepository) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
        this.config = config;
        this.personRepository = personRepository;
    }

    public List<PepPerson> findPersons(int limit) {
        Query query = new Query();

        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        query.limit(limit);

        query.skip(getSkipNumber());

        return mongoTemplate.find(query, PepPerson.class, config.getCollectionName());

    }

    private int getSkipNumber() {
        int skip = (int) personRepository.count();
        log.info("Fetched messages from {}", skip);
        return skip;
    }

}