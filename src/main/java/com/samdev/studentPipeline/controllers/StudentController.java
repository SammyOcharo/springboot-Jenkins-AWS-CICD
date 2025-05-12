package com.samdev.studentPipeline.controllers;

import com.samdev.studentPipeline.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("hello/")
    public String helloStudent(){
        return "Hello Student pipeline";
    }

    @GetMapping("all-students/")
    public List<String> allStudent(){
        return studentService.allStudents();
    }
}
