package com.mongo.demo.dao.impl;


import com.mongo.demo.dao.StudentDao;
import com.mongo.demo.dto.Student;

import com.mongo.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentRepository repository;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudent(int id) {
        Student student = repository.findById(id).orElse(null);
        return student;
    }

    public boolean deleteStudent(int id) {
        Student dm = repository.findById(id).orElse(null);
        if (dm != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student findByName(String name) {
        return repository.findByName(name);
    }

    public Student findByNameAndAge(String name, int age) {
        return repository.findByAgeAndName(age, name);
    }


}
