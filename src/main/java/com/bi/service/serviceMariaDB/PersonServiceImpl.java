package com.bi.service.serviceMariaDB;


import com.bi.service.model.mariadb.Country;
import com.bi.service.model.mariadb.Gender;
import com.bi.service.model.mariadb.Person;
import com.bi.service.model.mongodb.PepPerson;
import com.bi.service.repositoriesMariaDB.CountryRepository;
import com.bi.service.repositoriesMariaDB.GenderRepository;
import com.bi.service.repositoriesMariaDB.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private GenderRepository genderRepository;
    private CountryRepository countryRepository;
    private Map<String, Gender> genders = new HashMap<>();
    private Map<String, Country> countries = new HashMap<>();

    public PersonServiceImpl(PersonRepository personRepository, GenderRepository genderRepository, CountryRepository countryRepository) {
        this.personRepository = personRepository;
        this.genderRepository = genderRepository;
        this.countryRepository = countryRepository;

    }

    //TODO change pePerson.getGender to Unknown.
    @Override
    public Person addPerson(PepPerson pepPerson) {
        Person person = persistPerson(pepPerson);
        return person;
    }

    private Person persistPerson(PepPerson pepPerson) {

        Person person = new Person();

        person.setName(pepPerson.getFirstName());
        person.setLastName(pepPerson.getLastName());
        person.setAdditionalInfo(pepPerson.getIdentifier());

        person.setCountries(Arrays.asList(storeCountryIfNotExist(pepPerson.getCountry())));

        person.setGender(storeGenderIfNotExist(pepPerson.getGender()));

        personRepository.save(person);

        return person;

    }

    @Override
    public void initCache() {
        initGenders();
        initCountries();
    }

    private void initGenders() {
        genders.clear();
        List<Gender> gendersSync = genderRepository.findAll();


        for (Gender gender : gendersSync) {
            if (genders.get(gender.getName()) == null) {
                if (gender.getName() == null) {
                    genders.put("Unknown", gender);
                }

                genders.put(gender.getName(), gender);
            }
        }

    }

    private void initCountries() {
        countries.clear();

        List<Country> countriesSync = countryRepository.findAll();
        for (Country country : countriesSync) {
            if (countries.get(country.getName()) == null) {
                countries.put(country.getName(), country);
            }
        }

    }

    private Gender storeGenderIfNotExist(String gender) {
        gender = giveUnknowWhenEmpty(gender);

        Gender genderInDatabase = genders.get(gender);
        if (genderInDatabase != null) {
            return genderInDatabase;
        }
        Gender newGender = new Gender(gender);

        newGender = genderRepository.saveAndFlush(newGender);
        genders.put(gender, newGender);

        return newGender;
    }

    private Country storeCountryIfNotExist(String country) {

        Country countryInDatabase = countries.get(country);
        if (countryInDatabase != null) {
            return countryInDatabase;
        }
        Country newCountry = new Country(country);

        newCountry = countryRepository.saveAndFlush(newCountry);

        countries.put(country, newCountry);

        return newCountry;
    }

    private String giveUnknowWhenEmpty(String gender) {
        if (gender == null) {
            return "Unknown";
        }
        return gender;
    }

}