package com.example.demo.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ClassroomCoursePeriod implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Classroom classroom;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "course_id", referencedColumnName = "course_id"),
		@JoinColumn(name = "period_id", referencedColumnName = "period_id")
	})
	private CoursePeriod coursePeriod;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public CoursePeriod getCoursePeriod() {
		return coursePeriod;
	}

	public void setCoursePeriod(CoursePeriod coursePeriod) {
		this.coursePeriod = coursePeriod;
	}
	
	

}
