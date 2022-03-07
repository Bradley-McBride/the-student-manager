package com.the.student.manager.repositories;

import com.the.student.manager.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByIdNumber(String idNumber);

	List<Student> findByGrade(String grade);

	Long deleteByIdNumber(String idNumber);
}