package com.the.student.manager.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "STUDENT")
public class Student {
	
	@Id	
    @SequenceGenerator(name="SEQ1", sequenceName="SEQ1", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ1")
	@Column(name = "STUDENT_ID", nullable = false)
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ID_NUMBER")
	private String idNumber;

	@Column(name = "GRADE")
	private String grade;

	public Student() {
	}

	public Student(Long id, String firstName, String lastName, String idNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}