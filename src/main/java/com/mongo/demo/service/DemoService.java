package com.mongo.demo.service;

import com.mongo.demo.dao.DemoDao;
import com.mongo.demo.dto.Demo;
import com.mongo.demo.exception.IdNotFoundException;
import com.mongo.demo.response.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {

    @Autowired
    private DemoDao dao;
    public ResponseEntity<ResponseStructure<Demo>> savedemo(Demo demo) {
        return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure<>(HttpStatus.CREATED.value(), "Data saved",dao.saveDemo(demo)),HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<Demo>> getDemo(int id) {
        Demo demo=dao.getDemo(id);
        if(demo!=null)
        {
            return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found",demo),HttpStatus.FOUND);
        }
        throw new IdNotFoundException("Id Not Exist in db");
    }

    public ResponseEntity<ResponseStructure<String>> deleteDemo(int id) {
        boolean res=dao.deleteDemo(id);
        if(res) {
            return new ResponseEntity<ResponseStructure<String>>(new ResponseStructure(HttpStatus.NO_CONTENT.value(), "Data removed", null), HttpStatus.NOT_FOUND);
        }
        throw new IdNotFoundException("Id Not Exist in db");
    }

    public ResponseEntity<ResponseStructure<Demo>> updateDemo(Demo demo) {

            return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure<>(HttpStatus.OK.value(), "Data Updated",dao.saveDemo(demo)),HttpStatus.OK);
          }
    public ResponseEntity<ResponseStructure<Demo>> findByName(String name)
    {
        Demo list=dao.findByName(name);
        if(list!=null) {
            return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found", list), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure<>(HttpStatus.NOT_FOUND.value(), "Not Found",null),HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<ResponseStructure<List<Demo>>> findalldocs() {

        List<Demo> list=dao.getAllDemo();
        return new ResponseEntity<ResponseStructure<List<Demo>>>(new ResponseStructure<>(HttpStatus.FOUND.value(), "Data found",list),HttpStatus.FOUND);
    }

    public ResponseEntity<ResponseStructure<List<Demo>>> getByAgeGreaterThan(int age) {

       List<Demo> list2= dao.getAllDemo().stream().filter(s->s.getAge()>age).collect(Collectors.toList());
       return new ResponseEntity<ResponseStructure<List<Demo>>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found",list2),HttpStatus.FOUND);
    }

    public ResponseEntity<ResponseStructure<List<Demo>>> findByAgeSort() {
        List<Demo> list2= dao.getAllDemo().stream().sorted((s1,s2)->s1.getAge()-s2.getAge()).collect(Collectors.toList());
        return new ResponseEntity<ResponseStructure<List<Demo>>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Sorted",list2),HttpStatus.FOUND);

    }

    public ResponseEntity<ResponseStructure<Demo>> findByAgeAndName(String name, int age) {
        Demo demo=dao.findByNameAndAge(name,age);
        if(demo!=null)
        {
            return new ResponseEntity<ResponseStructure<Demo>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found",demo),HttpStatus.FOUND);
        }
        throw new IdNotFoundException("Id or name Not Exist in db");
    }
}
