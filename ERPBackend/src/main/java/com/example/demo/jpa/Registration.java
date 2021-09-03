package com.example.demo.jpa;

import java.io.Serializable;


import javax.persistence.*;


@Entity
public class Registration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "course_id", referencedColumnName = "course_id"),
		@JoinColumn(name = "period_id", referencedColumnName = "period_id")
	})
	private CoursePeriod coursePeriod;
	
	public Registration() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CoursePeriod getCoursePeriod() {
		return coursePeriod;
	}

	public void setCoursePeriod(CoursePeriod coursePeriod) {
		this.coursePeriod = coursePeriod;
	}


	
}
