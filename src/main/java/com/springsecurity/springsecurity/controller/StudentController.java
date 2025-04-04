package com.springsecurity.springsecurity.controller;

import com.springsecurity.springsecurity.model.Student;
import com.springsecurity.springsecurity.service.StudentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudentsList(), HttpStatus.OK);
    }

    @GetMapping("/csrftoken")
    public CsrfToken getcsrfToken(HttpServletRequest httpServletRequest){
       return (CsrfToken) httpServletRequest.getAttribute("_csrf");

    }

    @PostMapping("/addstudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.OK);
    }


}
