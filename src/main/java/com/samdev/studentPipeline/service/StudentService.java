package com.samdev.studentPipeline.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public List<String> allStudents() {
        return List.of("Sammy Ocharo", "Jaden Njuguna");
    }

    public List<String> allTeachers() {
        return List.of("Sammy Steve", "Jaden Steve");
    }

    public List<String> allWorkers() {
        return List.of("Sammy", "Jaden");
    }
}
