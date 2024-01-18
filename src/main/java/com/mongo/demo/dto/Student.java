package com.mongo.demo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Document(collection = "Students")
public class Student {
    private int id;
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name Can't be blank")
    @Size(min = 5,max = 8,message = "Name size must be between 5-8 characters")
    private String name;
    @NotNull(message = "age is mandatory")

    private int age;
    @NotNull(message = "city is mandatory")
    @NotBlank(message = "city Can't be blank")
    private String city;
}
