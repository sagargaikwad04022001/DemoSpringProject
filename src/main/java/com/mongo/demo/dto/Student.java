package com.mongo.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Students")
public class Student {
    private int id;
    private String name;
    private int age;
    private String city;
}
