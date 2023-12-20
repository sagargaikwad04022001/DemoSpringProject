package com.mongo.demo.repository;


import com.mongo.demo.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, Integer> {


    Student findByName();

    Student findByName(String name);

    @Query("{$and:[{'name':{$eq:?1}},{'age':{$eq:?0}}]}")
    public Student findByAgeAndName(int age, String name);

}
