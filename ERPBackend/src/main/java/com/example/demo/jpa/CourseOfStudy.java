	package com.example.demo.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "course_of_study")
public class CourseOfStudy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String financing_method;

	private int year;
	
	@ManyToOne
	private Student student;
	
	public CourseOfStudy() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getFinancing_method() {
		return financing_method;
	}

	public void setFinancing_method(String financing_method) {
		this.financing_method = financing_method;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
