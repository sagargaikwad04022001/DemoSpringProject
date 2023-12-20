package com.mongo.demo.controller;


import com.mongo.demo.dto.Student;
import com.mongo.demo.response.ResponseStructure;

import com.mongo.demo.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<ResponseStructure<Student>> saveBook(@RequestBody Student demo) {
        return studentService.saveStudent(demo);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<ResponseStructure<Student>> getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<ResponseStructure<Student>> updateDemo(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/student")
    public ResponseEntity<ResponseStructure<Student>> findByName(@RequestParam String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/getall")
    public ResponseEntity<ResponseStructure<List<Student>>> findall() {
        return studentService.findallStudents();
    }

    @GetMapping("/getbyage/{age}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByAge(@PathVariable int age) {
        return studentService.getByAgeGreaterThan(age);
    }

    @GetMapping("/getallbyage")
    public ResponseEntity<ResponseStructure<List<Student>>> findByAgeSort() {
        return studentService.findByAgeSort();
    }

    @GetMapping("/studenta")
    public ResponseEntity<ResponseStructure<Student>> findByAgeAndName(@RequestParam String name, @RequestParam int age) {
        return studentService.findByAgeAndName(name, age);
    }
}
