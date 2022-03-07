package com.the.student.manager.assemblers;

import com.the.student.manager.controller.StudentController;
import com.the.student.manager.entities.Student;
import com.the.student.manager.dto.StudentDto;
import com.the.student.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentAssembler extends RepresentationModelAssemblerSupport<Student, StudentDto> {

    @Autowired
    StudentService studentService;

    public StudentAssembler() {
        super(StudentController.class, StudentDto.class);
    }

    @Override
    public StudentDto toModel(Student entity) {
        StudentDto studentDto = instantiateModel(entity);

        studentDto.setId(entity.getId());
        studentDto.setFirstName(entity.getFirstName());
        studentDto.setLastName(entity.getLastName());
        studentDto.setIdNumber(entity.getIdNumber());
        studentDto.setGrade(entity.getGrade());

        return studentDto;
    }

    public List<StudentDto> listToModel(List<Student> entities) {
        List<StudentDto> studentDtos = new ArrayList<>();
        entities.forEach(student -> studentDtos.add(
                toModel(student)));
        return studentDtos;
    }
}