package com.mongo.demo.repository;

import com.mongo.demo.dto.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DemoRepository extends MongoRepository<Demo,Integer> {


    Demo findByName();

    Demo findByName(String name);

    @Query("{$and:[{'name':{$eq:?1}},{'age':{$eq:?0}}]}")
    public Demo findByAgeAndName(int age, String name);

}
