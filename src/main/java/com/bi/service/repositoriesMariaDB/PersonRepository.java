package com.bi.service.repositoriesMariaDB;

import com.bi.service.model.mariadb.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
