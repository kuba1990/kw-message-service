package com.bi.service.model.mariadb;


import javax.persistence.*;
import java.util.List;


@Table(name = "bi_person")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bi_person_country", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries;

    @ManyToOne
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
