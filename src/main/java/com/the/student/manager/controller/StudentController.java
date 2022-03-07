package com.the.student.manager.controller;

import com.the.student.manager.assemblers.StudentAssembler;
import com.the.student.manager.dto.StudentDto;
import com.the.student.manager.entities.Student;
import com.the.student.manager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    private StudentAssembler studentAssembler;

    @PostMapping("students/")
    @Operation(
            summary = "Adds a new student",
            description = "Adds a new student",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = StudentDto.class)))
                    ),
            }
    )
    public ResponseEntity<StudentDto> createStudent(@Parameter(description = "The student")
                                                    @RequestBody Student student) {

        Student studentEntity = studentService.createStudent(student);

        StudentDto studentDto = studentAssembler.toModel(studentEntity);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("students/{idNumber}")
    @Operation(
            summary = "Finds a student",
            description = "Finds a student by their Id number",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            }
    )
    public ResponseEntity<StudentDto> getByIdNumber(@Parameter(description = "The Id number of the student to find.") @PathVariable(value = "idNumber") String idNumber) {

        Student student = studentService.getStudentByIdNumber(idNumber);
        StudentDto studentDto = studentAssembler.toModel(student);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("students/grade/{grade}")
    @Operation(
            summary = "Finds a list of students based on the grade",
            description = "Finds a list of students by their grade",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = StudentDto.class)))
                    ),
            }
    )
    public ResponseEntity<List<StudentDto>> getStudentsByGrade(@Parameter(description = "The grade in which the student is")
                                                                 @PathVariable(value = "grade") String grade) {

        List<Student> studentEntities = studentService.getStudentsByGrade(grade);

        List<StudentDto> studentCollection = studentAssembler.listToModel(studentEntities);

        return new ResponseEntity<>(studentCollection, HttpStatus.OK);
    }

    @DeleteMapping("students/{idNumber}")
    @Operation(
            summary = "Deletes a student",
            description = "Deletes a student based on their Id number",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            }
    )
    @Transactional
    public ResponseEntity deleteStudent(@Parameter(description = "The Id number of the student to delete") @PathVariable(value = "idNumber") String idNumber) {

        studentService.deleteStudent(idNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}