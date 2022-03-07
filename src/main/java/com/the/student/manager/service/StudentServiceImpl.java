package com.the.student.manager.service;

import com.the.student.manager.entities.Student;
import com.the.student.manager.exception.DataNotFoundException;
import com.the.student.manager.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig(cacheNames = "studentCache")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Cacheable
    @Override
    public Student getStudentByIdNumber(String idNumber) {
        Student student = studentRepository.findByIdNumber(idNumber);
        if(student == null) {
            throw new DataNotFoundException("No Student Found");
        }
        return student;
    }

    @Cacheable
    @Override
    public List<Student> getStudentsByGrade(String state) {
        return studentRepository.findByGrade(state);
    }

    @Override
    public void deleteStudent(String idNumber) {
        studentRepository.deleteByIdNumber(idNumber);
    }
}