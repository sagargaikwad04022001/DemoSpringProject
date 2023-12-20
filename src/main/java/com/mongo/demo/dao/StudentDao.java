package com.mongo.demo.dao;

import com.mongo.demo.dto.Student;

import java.util.List;

public interface StudentDao {
    Student saveStudent(Student student);

    Student getStudent(int id);

    boolean deleteStudent(int id);

    List<Student> getAllStudents();

    Student findByName(String name);

    Student findByNameAndAge(String name, int age);
}
