package com.bi.service.model.mariadb;


import javax.persistence.*;


@Table(name = "bi_gender")
@Entity
public class Gender {

    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Gender() {
    }

    public Gender(String name) {
        this.name = name;
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
}
