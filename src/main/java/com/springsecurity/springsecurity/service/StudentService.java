package com.springsecurity.springsecurity.service;

import com.springsecurity.springsecurity.model.Student;
import com.springsecurity.springsecurity.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public List<Student> getStudentsList(){
        return studentRepo.findAll();

    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }
}
