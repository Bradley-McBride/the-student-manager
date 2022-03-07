package com.the.student.manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StudentDto extends RepresentationModel<StudentDto>{

	private Long id;

	private String firstName;

	private String lastName;

	private String idNumber;

	private String grade;
}