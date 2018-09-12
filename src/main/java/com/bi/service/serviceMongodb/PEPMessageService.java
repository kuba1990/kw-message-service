package com.bi.service.serviceMongodb;

import com.bi.service.model.mongodb.PepPerson;
import com.bi.service.repositoriesMariaDB.CountryRepository;
import com.bi.service.repositoriesMariaDB.GenderRepository;
import com.bi.service.repositoriesMariaDB.PersonRepository;
import com.bi.service.repositoriesMongoDB.PEPMessagesRepository;
import com.bi.service.serviceMariaDB.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PEPMessageService {

    private PEPMessagesRepository pepMessagesRepository;
    private PersonRepository personRepository;
    private GenderRepository genderRepository;
    private CountryRepository countryRepository;
    private PersonService personService;


    public PEPMessageService(PEPMessagesRepository pepMessagesRepository, PersonRepository personRepository, GenderRepository genderRepository, CountryRepository countryRepository, PersonService personService) {
        this.pepMessagesRepository = pepMessagesRepository;
        this.personRepository = personRepository;
        this.genderRepository = genderRepository;
        this.countryRepository = countryRepository;
        this.personService = personService;
    }

    @Transactional
    public List<PepPerson> migrate(int limit) {
        personService.initCache();
        List<PepPerson> pepPersons = pepMessagesRepository.findPersons(limit);
        for (int a = 0; a < pepPersons.size(); a++) {
            personService.addPerson(pepPersons.get(a));

        }
        return pepPersons;
    }

}




