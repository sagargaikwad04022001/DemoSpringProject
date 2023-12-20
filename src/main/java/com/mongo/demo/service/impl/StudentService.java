package com.mongo.demo.service.impl;


import com.mongo.demo.dao.StudentDao;

import com.mongo.demo.dto.Student;
import com.mongo.demo.exception.IdNotFoundException;
import com.mongo.demo.response.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements com.mongo.demo.service.StudentService {

    @Autowired
    private StudentDao studentDao;

    public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
        return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure<>(HttpStatus.CREATED.value(), "Data saved", studentDao.saveStudent(student)), HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<Student>> getStudent(int id) {
        Student student = studentDao.getStudent(id);
        if (student != null) {
            return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found", student), HttpStatus.FOUND);
        }
        throw new IdNotFoundException("Id Not Exist in db");
    }

    public ResponseEntity<ResponseStructure<String>> deleteStudent(int id) {
        boolean res = studentDao.deleteStudent(id);
        if (res) {
            return new ResponseEntity<ResponseStructure<String>>(new ResponseStructure(HttpStatus.NO_CONTENT.value(), "Data removed", null), HttpStatus.NOT_FOUND);
        }
        throw new IdNotFoundException("Id Not Exist in db");
    }

    public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {

        return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure<>(HttpStatus.OK.value(), "Data Updated", studentDao.saveStudent(student)), HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Student>> findByName(String name) {
        Student student = studentDao.findByName(name);
        if (student != null) {
            return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found", student), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure<>(HttpStatus.NOT_FOUND.value(), "Not Found", null), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<ResponseStructure<List<Student>>> findallStudents() {

        List<Student> list = studentDao.getAllStudents();
        return new ResponseEntity<ResponseStructure<List<Student>>>(new ResponseStructure<>(HttpStatus.FOUND.value(), "Data found", list), HttpStatus.FOUND);
    }

    public ResponseEntity<ResponseStructure<List<Student>>> getByAgeGreaterThan(int age) {

        List<Student> list2 = studentDao.getAllStudents().stream().filter(s -> s.getAge() > age).collect(Collectors.toList());
        return new ResponseEntity<ResponseStructure<List<Student>>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found", list2), HttpStatus.FOUND);
    }

    public ResponseEntity<ResponseStructure<List<Student>>> findByAgeSort() {
        List<Student> list2 = studentDao.getAllStudents().stream().sorted((s1, s2) -> s1.getAge() - s2.getAge()).collect(Collectors.toList());
        return new ResponseEntity<ResponseStructure<List<Student>>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Sorted", list2), HttpStatus.FOUND);

    }

    public ResponseEntity<ResponseStructure<Student>> findByAgeAndName(String name, int age) {
        Student student = studentDao.findByNameAndAge(name, age);
        if (student != null) {
            return new ResponseEntity<ResponseStructure<Student>>(new ResponseStructure(HttpStatus.FOUND.value(), "Data Found", student), HttpStatus.FOUND);
        }
        throw new IdNotFoundException("Id or name Not Exist in db");
    }
}
