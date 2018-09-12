package com.bi.service.serviceMariaDB;

import com.bi.service.model.mariadb.Person;
import com.bi.service.model.mongodb.PepPerson;

public interface PersonService {
    public Person addPerson(PepPerson pepPerson);

    public void initCache();
}

