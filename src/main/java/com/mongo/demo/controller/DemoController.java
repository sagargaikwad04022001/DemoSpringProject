package com.mongo.demo.controller;

import com.mongo.demo.dto.Demo;
import com.mongo.demo.response.ResponseStructure;
import com.mongo.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {


    @Autowired
    private DemoService service;

    @PostMapping("/demo")
    public ResponseEntity<ResponseStructure<Demo>> saveBook(@RequestBody Demo demo)
    {
        return service.savedemo(demo);
    }
    @GetMapping("/demo/{id}")
    public ResponseEntity<ResponseStructure<Demo>> getDemo(@PathVariable int id)
    {
        return service.getDemo(id);
    }
    @DeleteMapping("/demo/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteDemo(@PathVariable int id)
    {
        return service.deleteDemo(id);
    }
    @PutMapping("/demo/{id}")
    public ResponseEntity<ResponseStructure<Demo>> updateDemo(@RequestBody Demo demo)
    {
        return service.updateDemo(demo);
    }
    @GetMapping("/demo")
    public ResponseEntity<ResponseStructure<Demo>> findByName(@RequestParam String name)
    {
        return service.findByName(name);
    }

    @GetMapping("/getall")
    public ResponseEntity<ResponseStructure<List<Demo>>> findall()
    {
        return service.findalldocs();
    }
    @GetMapping("/getbyage/{age}")
    public ResponseEntity<ResponseStructure<List<Demo>>> findByAge(@PathVariable int age)
    {
        return service.getByAgeGreaterThan(age);
    }
    @GetMapping("/getallbyage")
    public ResponseEntity<ResponseStructure<List<Demo>>> findByAgeSort()
    {
        return service.findByAgeSort();
    }

    @GetMapping("/demoa")
    public ResponseEntity<ResponseStructure<Demo>> findByAgeAndName(@RequestParam String name,@RequestParam int age)
    {
        return service.findByAgeAndName(name,age);
    }
}
