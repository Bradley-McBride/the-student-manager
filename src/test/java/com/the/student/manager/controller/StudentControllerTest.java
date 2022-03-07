package com.the.student.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the.student.manager.dto.StudentDto;
import com.the.student.manager.entities.Student;
import com.the.student.manager.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    StudentController studentController;

    @MockBean
    StudentService studentService;

    @Test
    void when_IdIsFound_Expect_StudentDto() throws IOException {
        Mockito.when(studentService.getStudentByIdNumber("939393939")).thenReturn(getStudentByIdNumber());

        ResponseEntity<StudentDto> studentDtoResponseEntity = studentController.getByIdNumber("939393939");
        assertTrue(studentDtoResponseEntity.getStatusCode().is2xxSuccessful());

        StudentDto studentDto = studentDtoResponseEntity.getBody();
        assertNotNull(studentDto.getId());
        assertNotNull(studentDto.getFirstName());
        assertNotNull(studentDto.getLastName());
        assertNotNull(studentDto.getIdNumber());
        assertNotNull(studentDto.getGrade());
    }

    @Test
    void when_gradeIsFound_Expect_StudentDtoList() throws IOException {
        Mockito.when(studentService.getStudentsByGrade(Mockito.anyString())).thenReturn(getStudentsByGrade());

        ResponseEntity<List<StudentDto>> studentDtoResponseEntity = studentController.getStudentsByGrade("1");

        assertTrue(studentDtoResponseEntity.getStatusCode().is2xxSuccessful());
        studentDtoResponseEntity.getBody().forEach(studentDto -> {
            assertNotNull(studentDto.getId());
            assertNotNull(studentDto.getFirstName());
            assertNotNull(studentDto.getLastName());
            assertNotNull(studentDto.getIdNumber());
            assertNotNull(studentDto.getGrade());
        });
    }

    private Student getStudentByIdNumber() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/student-by-id-number.json").getFile();
        Student student = objectMapper.readValue(
                resource, Student.class);
        return student;
    }

    private List<Student> getStudentsByGrade() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/students-by-grade.json").getFile();
        Student[] students = objectMapper.readValue(
                resource, Student[].class);
        return Arrays.asList(students);
    }
}
