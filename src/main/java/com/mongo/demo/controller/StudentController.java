package com.mongo.demo.controller;


import com.mongo.demo.dto.Student;
import com.mongo.demo.response.ResponseStructure;

import com.mongo.demo.service.impl.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class StudentController {

    Logger logger= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;


    @PostMapping("/student")
    public ResponseEntity<ResponseStructure<Student>> saveBook(@Valid @RequestBody Student demo) {
        logger.info("This is info message");
        return studentService.saveStudent(demo);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<ResponseStructure<Student>> getStudent(@PathVariable int id) {
        logger.info("This is info message");
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> catchErrors(MethodArgumentNotValidException exception)
    {
        Map<String,String> errors=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String fieldError=error.getDefaultMessage();
            errors.put(fieldName,fieldError);
        });
        return errors;
    }
}
