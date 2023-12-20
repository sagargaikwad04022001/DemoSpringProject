package com.mongo.demo.dao;

import com.mongo.demo.dto.Demo;
import com.mongo.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoDao {

    @Autowired
    private DemoRepository repository;
    public Demo saveDemo(Demo demo)
    {
        return repository.save(demo);
    }
    public Demo getDemo(int id)
    {
        Demo demo= repository.findById(id).orElse(null);
        return demo;
    }

    public boolean deleteDemo(int id)
    {
       Demo dm= repository.findById(id).orElse(null);
       if(dm!=null) {
           repository.deleteById(id);
           return true;
       }
       return false;
    }
    public List<Demo> getAllDemo()
    {
        return repository.findAll();
    }
    public Demo findByName(String name)
    {
        return repository.findByName(name);
    }

    public Demo findByNameAndAge(String name, int age) {
        return repository.findByAgeAndName(age,name);
    }


}
