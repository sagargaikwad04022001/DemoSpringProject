package com.mongo.demo.service;

import com.mongo.demo.dto.Student;
import com.mongo.demo.response.ResponseStructure;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<ResponseStructure<Student>> saveStudent(Student student);

    ResponseEntity<ResponseStructure<Student>> getStudent(int id);

    ResponseEntity<ResponseStructure<String>> deleteStudent(int id);

    ResponseEntity<ResponseStructure<Student>> updateStudent(Student student);

    ResponseEntity<ResponseStructure<Student>> findByName(String name);

    ResponseEntity<ResponseStructure<List<Student>>> findallStudents();

    ResponseEntity<ResponseStructure<List<Student>>> getByAgeGreaterThan(int age);

    ResponseEntity<ResponseStructure<List<Student>>> findByAgeSort();

    ResponseEntity<ResponseStructure<Student>> findByAgeAndName(String name, int age);

}
