package com.the.student.manager.service;

import com.the.student.manager.entities.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudentByIdNumber(String idNumber);

    List<Student> getStudentsByGrade(String state);

    void deleteStudent(String idNumber);
}