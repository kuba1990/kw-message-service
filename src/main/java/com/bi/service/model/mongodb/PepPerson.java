package com.bi.service.model.mongodb;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PepPerson {

    private ObjectId _id;
    private String identifier;
    private String title;
    private String gender;
    private String firstName;
    private String lastName;
    private String alternativeScript;
    private String function;
    private String category;
    private String country;
    private String recordType;
    private String specificFunction;


    @Override
    public String toString() {
        return "PepPerson{" +
                "_id=" + _id +
                ", identifier='" + identifier + '\'' +
                ", title='" + title + '\'' +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alternativeScript='" + alternativeScript + '\'' +
                ", function='" + function + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", recordType='" + recordType + '\'' +
                ", specificFunction='" + specificFunction + '\'' +
                '}';
    }
}
